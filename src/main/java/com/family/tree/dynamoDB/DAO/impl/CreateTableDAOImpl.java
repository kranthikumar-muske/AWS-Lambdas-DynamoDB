package com.family.tree.dynamoDB.DAO.impl;

import javax.inject.Inject;
import javax.inject.Provider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.TableStatus;
import com.family.tree.dynamoDB.DAO.CreateTableDAO;

import software.amazon.awssdk.utils.Logger;

public class CreateTableDAOImpl implements CreateTableDAO {

	private Provider<AmazonDynamoDB> dynamoDbClient;
	private Provider<DynamoDBMapper> dynamoDBMapper;
	
	@Inject
	public CreateTableDAOImpl(Provider<AmazonDynamoDB> dynamoDbClient, Provider<DynamoDBMapper> dynamoDBMapper) {
		this.dynamoDbClient = dynamoDbClient;//TODO get mapper and query runners here.
		this.dynamoDBMapper = dynamoDBMapper;
	}
	

	@Override
	public void createTable(Class<?> pojoClass) {
		CreateTableRequest createTableRequest = dynamoDBMapper.get().generateCreateTableRequest(pojoClass);
		createTableRequest.withProvisionedThroughput(new ProvisionedThroughput(1L,1L));
		createTableRequest.getGlobalSecondaryIndexes().get(0).setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		createTableRequest.getGlobalSecondaryIndexes().get(1).setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		createTableRequest.getGlobalSecondaryIndexes().get(2).setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		
		if(tableExists(createTableRequest.getTableName())) {
			Logger.loggerFor(getClass()).logger().info("table already exists!");
			return;
		}
		
		Logger.loggerFor(getClass()).logger().info("table doesn't exist creating now");
		dynamoDbClient.get().createTable(createTableRequest);
		waitForTableCreated(createTableRequest.getTableName());
	}
	
	private boolean tableExists(String tableName) {
		try {
			dynamoDbClient.get().describeTable(tableName);
			return true;
		}catch(ResourceNotFoundException e) {
			return false;
		}
	}
	
	private void waitForTableCreated(String tableName) {
	while(true) {
		try {
			Logger.loggerFor(getClass()).logger().info("table not created yet. waiting 2000 ms");
			Thread.sleep(2000);
			TableDescription table = dynamoDbClient.get().describeTable(tableName).getTable();
			if(table==null)
				continue;
			
			String tableStatus = table.getTableStatus();
			if(tableStatus.equals(TableStatus.ACTIVE.toString())) {
				Logger.loggerFor(getClass()).logger().info("table is created now and active");
				return;
			}
		}catch(ResourceNotFoundException e) {
			Logger.loggerFor(getClass()).logger().info("table still not created waiting...");
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	}

}
