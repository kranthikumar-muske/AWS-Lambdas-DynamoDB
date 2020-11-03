package com.family.tree.dynamoDB.DAO.impl;

import javax.inject.Inject;
import javax.inject.Provider;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import com.family.tree.dynamoDB.DAO.PutDAO;
import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;

public class PutDAOImpl implements PutDAO{
	private Provider<DynamoDBMapper> dynamoDBMapper;
	
	@Inject
	public PutDAOImpl(Provider<DynamoDBMapper> dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	@Override
	public void createUserProfile(FamilyTreeTablePOJO familyTreeTablePOJO) {
		dynamoDBMapper.get().save(familyTreeTablePOJO);		
	}

	@Override
	public void createNewChildAndlinkToParents(FamilyTreeTablePOJO childDetails,
			FamilyTreeTablePOJO linkToFatherDetails, FamilyTreeTablePOJO linkToMotherDetails) {
		
		TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
		transactionWriteRequest.addPut(childDetails);
		transactionWriteRequest.addPut(linkToFatherDetails);
		transactionWriteRequest.addPut(linkToMotherDetails);
		
		dynamoDBMapper.get().transactionWrite(transactionWriteRequest);
	}


	@Override
	public void createNewSpouseAndLinkToExistingPerson(FamilyTreeTablePOJO spouseDetails,
			FamilyTreeTablePOJO linkSpouseToExistingPerson, FamilyTreeTablePOJO linkExistingPersonToSpouse) {
		
		TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
		transactionWriteRequest.addPut(spouseDetails);
		transactionWriteRequest.addPut(linkSpouseToExistingPerson);
		transactionWriteRequest.addPut(linkExistingPersonToSpouse);
		
		dynamoDBMapper.get().transactionWrite(transactionWriteRequest);
	}

	@Override
	public void createNewParentsAndLinkToChild(FamilyTreeTablePOJO fatherDetails, FamilyTreeTablePOJO motherDetails,
			FamilyTreeTablePOJO linkChildToFather, FamilyTreeTablePOJO linkChildToMother,FamilyTreeTablePOJO linkFatherToMother,
			FamilyTreeTablePOJO linkMotherToFather) {
		
		TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
		transactionWriteRequest.addPut(fatherDetails);
		transactionWriteRequest.addPut(motherDetails);
		transactionWriteRequest.addPut(linkChildToFather);
		transactionWriteRequest.addPut(linkChildToMother);
		transactionWriteRequest.addPut(linkFatherToMother);
		transactionWriteRequest.addPut(linkMotherToFather);
		
		dynamoDBMapper.get().transactionWrite(transactionWriteRequest);
		
	}
	


}
