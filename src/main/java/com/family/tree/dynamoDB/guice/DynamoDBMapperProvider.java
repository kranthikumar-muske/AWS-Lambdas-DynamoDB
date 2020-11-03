package com.family.tree.dynamoDB.guice;

import javax.inject.Inject;
import javax.inject.Provider;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


public class DynamoDBMapperProvider implements Provider<DynamoDBMapper>{
	private Provider<AmazonDynamoDB> dynamoDBClientProvider;
	
	@Inject
	public DynamoDBMapperProvider(Provider<AmazonDynamoDB> dynamoDBClientProvider) {
		this.dynamoDBClientProvider = dynamoDBClientProvider;
	}

	@Override
	public DynamoDBMapper get() {
		return new DynamoDBMapper(dynamoDBClientProvider.get());
	}

}
