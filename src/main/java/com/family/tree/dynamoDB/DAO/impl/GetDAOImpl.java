package com.family.tree.dynamoDB.DAO.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.KeyPair;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.family.tree.dynamoDB.DAO.GetDAO;
import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;
import com.family.tree.dynamoDB.table.mapping.ReplyPOJO;

public class GetDAOImpl implements GetDAO{

	private Provider<DynamoDBMapper> dynamoDBMapper;
	private Provider<DynamoDBQueryExpression<FamilyTreeTablePOJO>> dynamoDBQueryExpression;
	
	@Inject
	public GetDAOImpl(Provider<DynamoDBMapper> dynamoDBMapper, Provider<DynamoDBQueryExpression<FamilyTreeTablePOJO>> dynamoDBQueryExpression) {
		this.dynamoDBMapper = dynamoDBMapper;
		this.dynamoDBQueryExpression = dynamoDBQueryExpression;
	}

	@Override
	public List<ReplyPOJO> getChildrensByParentId(String parentUserId) {
		DynamoDBQueryExpression<FamilyTreeTablePOJO> dynamoDBQueryExpression1 = dynamoDBQueryExpression.get();
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":userId", new AttributeValue().withS(parentUserId));
		eav.put(":relationship", new AttributeValue().withS("child_"));
		
		dynamoDBQueryExpression1.
		withKeyConditionExpression(
				"userId = :userId AND begins_with(Relationship, :relationship)")
		.withExpressionAttributeValues(eav);
		
		List<FamilyTreeTablePOJO> childrenKeys = dynamoDBMapper.get().query(FamilyTreeTablePOJO.class, dynamoDBQueryExpression1);
		
		List<String> childUids = new ArrayList<String>();
		for(FamilyTreeTablePOJO childrenKey:childrenKeys) {
			childUids.add(childrenKey.getChildrenkey());
		}
		
		return getPersonsDetailsWithSpouses(childUids);
	}

	@Override
	public List<ReplyPOJO> getProfileById(String userId) {
		List<ReplyPOJO>	returnlist =	
				getPersonsDetailsWithSpouses(Arrays.asList(userId));
		return returnlist;
	}

	@Override
	public List<ReplyPOJO> getParentsInfoByChildId(String childUserId) {
		DynamoDBQueryExpression<FamilyTreeTablePOJO> dynamoDBQueryExpression1 = dynamoDBQueryExpression.get();
		
		FamilyTreeTablePOJO familyTreeTablePOJO = new FamilyTreeTablePOJO();
		familyTreeTablePOJO.setChildrenkey(childUserId);
		
		dynamoDBQueryExpression1.
		withHashKeyValues(familyTreeTablePOJO).withConsistentRead(false).withScanIndexForward(false);
		
		List<FamilyTreeTablePOJO> parentsKeys = dynamoDBMapper.get().query(FamilyTreeTablePOJO.class, dynamoDBQueryExpression1);
		
		return getPersonsDetailsWithSpouses(Arrays.asList(parentsKeys.get(0).getUserId()));
	}

	/**
	 * 
	 * @param userIds
	 * @return list of user Ids details with spouse details
	 */
	public List<ReplyPOJO> getPersonsDetailsWithSpouses(List<String> userIds){
		
		List<ReplyPOJO> replyPOJOs = new ArrayList<ReplyPOJO>();
		
		FamilyTreeTablePOJO myself = null;
		FamilyTreeTablePOJO spouse = null;
		
		List<KeyPair> keyPairList = new ArrayList<>();
		
		for(String userId:userIds) {
		
		KeyPair myselfKeyPair = new KeyPair();
		myselfKeyPair.withHashKey(userId);
		myselfKeyPair.withRangeKey("myself");

	    KeyPair spouseKeyPair = new KeyPair();
	    spouseKeyPair.withHashKey(userId);
	    spouseKeyPair.withRangeKey("spouse");
	    
	    keyPairList.add(myselfKeyPair);
	    keyPairList.add(spouseKeyPair);
		}
	    
	    Map<Class<?>, List<KeyPair>> keyPairForTable = new HashMap<>();
	    keyPairForTable.put(FamilyTreeTablePOJO.class, keyPairList);

	    Map<String, List<Object>> batchResults = dynamoDBMapper.get().batchLoad(keyPairForTable);
	    
	    for (Map.Entry<String, List<Object>> entry : batchResults.entrySet()) {
	    	
	    	for(Object tree: entry.getValue()) {
	    	   FamilyTreeTablePOJO familyTreeTablePOJO= (FamilyTreeTablePOJO) tree;
	    	   if(familyTreeTablePOJO.getRelationship().equals("myself")) {
	    		   myself = familyTreeTablePOJO;
	    	   }else {
	    		   continue;
	    	   }
	    	   
	    	   for(Object tree2: entry.getValue()) {
	    		   FamilyTreeTablePOJO familyTreeTablePOJO2= (FamilyTreeTablePOJO) tree2;
	    		   if(familyTreeTablePOJO2.getRelationship().equals("spouse") && familyTreeTablePOJO2.getUserId().equals(myself.getUserId())) {
		    		   spouse = dynamoDBMapper.get().load(FamilyTreeTablePOJO.class, familyTreeTablePOJO2.getSpousekey(), "myself");
		    		   break;
		    	   }   
	    	   }
	    	   ReplyPOJO replyPOJO = new ReplyPOJO();
	    	   replyPOJO.setMyself(myself);
	    	   replyPOJO.setSpouse(spouse);
	    	   replyPOJOs.add(replyPOJO);
	       }
	    }
	    
	    return replyPOJOs;
	}

	
	@Override
	public List<ReplyPOJO> getProfileByName(String firstName) {
		
		   Map<String, String> attributeNames = new HashMap<String, String>();
		   attributeNames.put("#firstname", "First Name");
		 
		    Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
		    attributeValues.put(":name", new AttributeValue().withS(firstName));
		 
		    DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression()
		            .withFilterExpression("begins_with(#firstname, :name)")
		            .withExpressionAttributeNames(attributeNames)
		            .withExpressionAttributeValues(attributeValues);
		 
		    List<FamilyTreeTablePOJO> profiles = dynamoDBMapper.get().scan(FamilyTreeTablePOJO.class, dynamoDBScanExpression);
		    
		    List<String> userIds = new ArrayList<String>();
		    for(FamilyTreeTablePOJO familyTreeTablePOJO: profiles) {
		    	userIds.add(familyTreeTablePOJO.getUserId());
		    }
		    
		    return getPersonsDetailsWithSpouses(userIds);
	}
}
