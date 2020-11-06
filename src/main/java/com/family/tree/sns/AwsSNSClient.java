package com.family.tree.sns;

import java.util.List;

import software.amazon.awssdk.services.sns.model.Topic;

public interface AwsSNSClient {
	
	List<Topic> getListOfExistingTopics();
	
	String createTopic(String topicName);
	
	void createMailSubscription(String topicARN, String emailId);
	
	void createSMSSubscription(String topicARN, String phoneNumber);
	
	void publishTopic(String topicARN, String message);
	
	void unSubscribeFromTopic(String subscriptionToken);
	
	void deleteTopic(String topicARN);
	
}
