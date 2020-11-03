package com.family.tree.lambda.functions.deleteOps;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.family.tree.dynamoDB.DAO.DeleteDAO;
import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.lambda.functions.putOps.FamilyTreeSetters;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DeleteUserProfileLambda extends FamilyTreeSetters implements RequestHandler<DeleteHttpRequest, DeleteHttpResponse> {
	private DeleteDAO deleteDAO;

	
	public DeleteUserProfileLambda() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.deleteDAO = injector.getInstance(DeleteDAO.class);
	}
	
    @Override
    public DeleteHttpResponse handleRequest(DeleteHttpRequest request, Context context) {
        context.getLogger().log("Input: " + request);

        //TODO
        deleteDAO.deleteById();
        
        return new DeleteHttpResponse();
    }
}