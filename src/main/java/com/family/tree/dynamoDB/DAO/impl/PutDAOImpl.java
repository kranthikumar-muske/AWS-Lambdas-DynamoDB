package com.family.tree.dynamoDB.DAO.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import com.family.tree.dynamoDB.DAO.PutDAO;
import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;
import com.family.tree.sns.AwsSNSClient;

import software.amazon.awssdk.services.sns.model.Topic;

public class PutDAOImpl implements PutDAO{
	private Provider<DynamoDBMapper> dynamoDBMapper;
	private AwsSNSClient awsSnsClient;
	
	@Inject
	public PutDAOImpl(Provider<DynamoDBMapper> dynamoDBMapper, AwsSNSClient awsSnsClient) {
		this.dynamoDBMapper = dynamoDBMapper;
		this.awsSnsClient = awsSnsClient;
	}

	@Override
	public void createUserProfile(FamilyTreeTablePOJO familyTreeTablePOJO) {
		dynamoDBMapper.get().save(familyTreeTablePOJO);
		if(!familyTreeTablePOJO.getFirstName().isEmpty()) {
			notifyAdmin(familyTreeTablePOJO.getFirstName()+ "," +familyTreeTablePOJO.getLastName());
		}
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
	

	private void notifyAdmin(String profileName) {
		String topicName = "FamilyTree";
		String topicARN = createTopicIfAlreadyNotCreated(topicName);
		awsSnsClient.publishTopic(topicARN, "A new profile for: "+ profileName +" has been created using your family tree application");
	}
	
	private String createTopicIfAlreadyNotCreated(String topicName) {
		List<Topic> topics = awsSnsClient.getListOfExistingTopics();
		for(Topic topic: topics) {
			String topicARN = topic.topicArn();
			if(topicARN.contains(topicName)) {
				return topicARN;
			}
		}
		return createTopicAndSubscriptionsForSNS(topicName);
	}
	
	private String createTopicAndSubscriptionsForSNS(String topicName) {
		String topicARN = awsSnsClient.createTopic("FamilyTree");
		awsSnsClient.createMailSubscription(topicARN, "kranthikumar.muske@gmail.com");
		awsSnsClient.createSMSSubscription(topicARN, "+19136890860");
		return topicARN;
	}
}