package com.family.tree.sns;

import org.junit.Test;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.DeleteTopicRequest;
import software.amazon.awssdk.services.sns.model.DeleteTopicResponse;
import software.amazon.awssdk.services.sns.model.ListTopicsRequest;
import software.amazon.awssdk.services.sns.model.ListTopicsResponse;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
import software.amazon.awssdk.services.sns.model.UnsubscribeRequest;
import software.amazon.awssdk.services.sns.model.UnsubscribeResponse;

public class TestSNS {
	
	@Test
	public void test() {
		SnsClient sns = SnsClient.builder().region(Region.US_EAST_2).build();
		//String arn = createSNSTopic(sns,"Family");
		//subEmail(sns,arn,"kranthikumar.muske@gmail.com");
		//subPhone(sns,arn,"+19136890860");
		//pubTopic(sns, "wowwwwwwwww2", "arn:aws:sns:us-east-2:224619513979:Family");
		listSNSTopics(sns);
	}
	
	
	
	
	public static String createSNSTopic(SnsClient snsClient, String topicName ) {

	    CreateTopicResponse result = null;
	    try {
	        CreateTopicRequest request = CreateTopicRequest.builder()
	                .name(topicName)
	                .build();

	        result = snsClient.createTopic(request);
	        return result.topicArn();
	    } catch (SnsException e) {

	        System.err.println(e.awsErrorDetails().errorMessage());
	        System.exit(1);
	    }
	    return "";
	}
	
	public static void listSNSTopics(SnsClient snsClient) {

	    try {
	        ListTopicsRequest request = ListTopicsRequest.builder()
	                .build();

	        ListTopicsResponse result = snsClient.listTopics(request);
	        System.out.println("Status was " + result.sdkHttpResponse().statusCode() + "\n\nTopics\n\n" + result.topics());

	} catch (SnsException e) {

	    System.err.println(e.awsErrorDetails().errorMessage());
	    System.exit(1);
	}
	}
	
	    public static void subEmail(SnsClient snsClient, String topicArn, String email) {

	        try {
	            SubscribeRequest request = SubscribeRequest.builder()
	                .protocol("email")
	                .endpoint(email)
	                .returnSubscriptionArn(true)
	                .topicArn(topicArn)
	                .build();

	            SubscribeResponse result = snsClient.subscribe(request);
	            System.out.println("Subscription ARN: " + result.subscriptionArn() + "\n\n Status was " + result.sdkHttpResponse().statusCode());

	        } catch (SnsException e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	            System.exit(1);
	        }
	    }
	    
	    
	    public static void subPhone(SnsClient snsClient, String topicArn, String phoneNumber) {

	        try {
	            SubscribeRequest request = SubscribeRequest.builder()
	                .protocol("sms")
	                .endpoint(phoneNumber)
	                .returnSubscriptionArn(true)
	                .topicArn(topicArn)
	                .build();

	            SubscribeResponse result = snsClient.subscribe(request);
	            System.out.println("Subscription ARN: " + result.subscriptionArn() + "\n\n Status was " + result.sdkHttpResponse().statusCode());

	        } catch (SnsException e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	            System.exit(1);
	        }
	    }
	    
	    public static void pubTopic(SnsClient snsClient, String message, String topicArn) {

	        try {
	            PublishRequest request = PublishRequest.builder()
	                .message(message)
	                .topicArn(topicArn)
	                .build();

	            PublishResponse result = snsClient.publish(request);
	            System.out.println(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());

	         } catch (SnsException e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	              System.exit(1);
	         }
	        
	    }
	    
	    
	    public static void unSub(SnsClient snsClient, String subscriptionToken) {

	        try {
	            UnsubscribeRequest request = UnsubscribeRequest.builder()
	                .subscriptionArn(subscriptionToken)
	                .build();

	            UnsubscribeResponse result = snsClient.unsubscribe(request);

	            System.out.println("\n\nStatus was " + result.sdkHttpResponse().statusCode()
	                + "\n\nSubscription was removed for " + request.subscriptionArn());

	        } catch (SnsException e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	            System.exit(1);
	        }
	    }
	    
	    public static void deleteSNSTopic(SnsClient snsClient, String topicArn ) {

	        try {
	            DeleteTopicRequest request = DeleteTopicRequest.builder()
	                .topicArn(topicArn)
	                .build();

	            DeleteTopicResponse result = snsClient.deleteTopic(request);
	            System.out.println("\n\nStatus was " + result.sdkHttpResponse().statusCode());

	        } catch (SnsException e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	            System.exit(1);
	        }
	    }
}
