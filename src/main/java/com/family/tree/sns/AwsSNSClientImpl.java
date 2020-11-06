package com.family.tree.sns;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.DeleteTopicRequest;
import software.amazon.awssdk.services.sns.model.ListTopicsRequest;
import software.amazon.awssdk.services.sns.model.ListTopicsResponse;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.Topic;
import software.amazon.awssdk.services.sns.model.UnsubscribeRequest;

public class AwsSNSClientImpl implements AwsSNSClient{
	
	private Provider<SnsClient> snsClient;

	
	@Inject
	public AwsSNSClientImpl(Provider<SnsClient> snsClient) {
		this.snsClient = snsClient;
	}


	@Override
	public List<Topic> getListOfExistingTopics() {
		 try {
		      ListTopicsRequest request = ListTopicsRequest.builder().build();
		      ListTopicsResponse listTopicsResponse = snsClient.get().listTopics(request);
		      return listTopicsResponse.topics();
		 } catch (SnsException e) {
		   throw new RuntimeException("error getting list of topics");
		}
	}


	@Override
	public String createTopic(String topicName) {
	    CreateTopicResponse result = null;
	    try {
	        CreateTopicRequest request = CreateTopicRequest.builder().name(topicName).build();
	        result = snsClient.get().createTopic(request);
	        return result.topicArn();
	    } catch (SnsException e) {
			   throw new RuntimeException("error creating topic");
		}
	}


	@Override
	public void createMailSubscription(String topicARN, String emailId) {
        try {
            SubscribeRequest request = SubscribeRequest.builder()
                .protocol("email")
                .endpoint(emailId)
                .returnSubscriptionArn(true)
                .topicArn(topicARN)
                .build();

            snsClient.get().subscribe(request);
	    } catch (SnsException e) {
			   throw new RuntimeException("error creating mail subscriptions");
		}
	}


	@Override
	public void createSMSSubscription(String topicARN, String phoneNumber) {
        try {
            SubscribeRequest request = SubscribeRequest.builder()
                .protocol("sms")
                .endpoint(phoneNumber)
                .returnSubscriptionArn(true)
                .topicArn(topicARN)
                .build();

             snsClient.get().subscribe(request);
        } catch (SnsException e) {
			   throw new RuntimeException("error creating sms subscriptions");
		}
	}


	@Override
	public void publishTopic(String topicARN, String message) {
        try {
            PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn(topicARN)
                .build();

            snsClient.get().publish(request);
        } catch (SnsException e) {
			   throw new RuntimeException("error publishing message");
		}
	}


	@Override
	public void unSubscribeFromTopic(String subscriptionToken) {
		 try {
	            UnsubscribeRequest request = UnsubscribeRequest.builder()
	                .subscriptionArn(subscriptionToken)
	                .build();

	            snsClient.get().unsubscribe(request);
		 }
	            catch (SnsException e) {
	 			   throw new RuntimeException("error occured unsubscibing from topic");
	 		}
	}


	@Override
	public void deleteTopic(String topicARN) {
		try {
            DeleteTopicRequest request = DeleteTopicRequest.builder()
                .topicArn(topicARN)
                .build();

            snsClient.get().deleteTopic(request);
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
	}
}
