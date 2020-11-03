package com.family.tree.lambda.functions.updateOps;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.family.tree.dynamoDB.DAO.UpdateDAO;
import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.lambda.functions.putOps.FamilyTreeSetters;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class UpdateUserProfileLambda extends FamilyTreeSetters implements RequestHandler<UpdateHttpRequest, UpdateHttpResponse> {
	private UpdateDAO updateDAO;
	
	public UpdateUserProfileLambda() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.updateDAO = injector.getInstance(UpdateDAO.class);
	}
	
    @Override
    public UpdateHttpResponse handleRequest(UpdateHttpRequest request, Context context) {
        context.getLogger().log("Input: " + request);

        //TODO
        updateDAO.updateById();
        
        return new UpdateHttpResponse();
    }
}