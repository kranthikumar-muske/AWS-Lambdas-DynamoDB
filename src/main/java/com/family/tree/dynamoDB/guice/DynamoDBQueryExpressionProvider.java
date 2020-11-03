package com.family.tree.dynamoDB.guice;

import javax.inject.Provider;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;


public class DynamoDBQueryExpressionProvider implements Provider<DynamoDBQueryExpression<FamilyTreeTablePOJO>>{

	@Override
	public DynamoDBQueryExpression<FamilyTreeTablePOJO> get() {
		return new DynamoDBQueryExpression<FamilyTreeTablePOJO>();
	}

}
