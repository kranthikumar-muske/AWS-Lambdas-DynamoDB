package com.family.tree.dynamoDB.DAO.impl;

import javax.inject.Inject;
import javax.inject.Provider;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.family.tree.dynamoDB.DAO.UpdateDAO;

public class UpdateDAOImpl implements UpdateDAO{
	private Provider<AmazonDynamoDB> dynamoDbClient;
	
	@Inject
	public UpdateDAOImpl(Provider<AmazonDynamoDB> dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
	}

	@Override
	public void updateById() {
		// TODO Auto-generated method stub
		
	}

}
