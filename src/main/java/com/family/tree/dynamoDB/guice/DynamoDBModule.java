package com.family.tree.dynamoDB.guice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.family.tree.dynamoDB.DAO.CreateTableDAO;
import com.family.tree.dynamoDB.DAO.DeleteDAO;
import com.family.tree.dynamoDB.DAO.GetDAO;
import com.family.tree.dynamoDB.DAO.PutDAO;
import com.family.tree.dynamoDB.DAO.UpdateDAO;
import com.family.tree.dynamoDB.DAO.impl.CreateTableDAOImpl;
import com.family.tree.dynamoDB.DAO.impl.DeleteDAOImpl;
import com.family.tree.dynamoDB.DAO.impl.GetDAOImpl;
import com.family.tree.dynamoDB.DAO.impl.PutDAOImpl;
import com.family.tree.dynamoDB.DAO.impl.UpdateDAOImpl;
import com.family.tree.dynamoDB.util.GenerateRandomUUID;
import com.google.inject.AbstractModule;

public class DynamoDBModule extends AbstractModule{
	
	@Override
	protected void configure() {
		bind(AmazonDynamoDB.class).toProvider(DynamoDBClientProvider.class);
		bind(DynamoDBMapper.class).toProvider(DynamoDBMapperProvider.class);
		bind(CreateTableDAO.class).to(CreateTableDAOImpl.class);
		bind(GetDAO.class).to(GetDAOImpl.class);
		bind(PutDAO.class).to(PutDAOImpl.class);
		bind(UpdateDAO.class).to(UpdateDAOImpl.class);
		bind(DeleteDAO.class).to(DeleteDAOImpl.class);
		bind(GenerateRandomUUID.class).toInstance(new GenerateRandomUUID());
	}

}
