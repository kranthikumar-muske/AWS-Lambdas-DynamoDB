package com.family.tree.dynamoDB.DAO.impl;

import javax.inject.Inject;
import javax.inject.Provider;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.family.tree.dynamoDB.DAO.DeleteDAO;


public class DeleteDAOImpl implements DeleteDAO{
	
	private final Provider<AmazonDynamoDB> dynamoDbClient;
	
	@Inject
	public DeleteDAOImpl(final Provider<AmazonDynamoDB> dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
	}

	@Override
	public void deleteById() {
		// TODO Auto-generated method stub
		
	}

}
