package com.family.tree.dynamoDB.guice;

import javax.inject.Provider;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBClientProvider implements Provider<AmazonDynamoDB>{
	
	  @Override
	  public AmazonDynamoDB get() {
	return AmazonDynamoDBClientBuilder.standard()
	        .withRegion(Regions.US_EAST_2)
	        .build();
}
}