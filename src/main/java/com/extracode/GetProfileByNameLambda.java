package com.extracode;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.family.tree.dynamoDB.DAO.GetDAO;
import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.dynamoDB.table.mapping.ReplyPOJO;
import com.family.tree.lambda.functions.getOps.replys.GetHttpResponse;
import com.family.tree.lambda.functions.getOps.requests.GetHttpRequest;
import com.family.tree.lambda.functions.putOps.FamilyTreeSetters;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GetProfileByNameLambda extends FamilyTreeSetters implements RequestHandler<GetHttpRequest, GetHttpResponse> {
	private GetDAO getDAO;
	
	public GetProfileByNameLambda() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.getDAO = injector.getInstance(GetDAO.class);
	}
	
    @Override
    public GetHttpResponse handleRequest(GetHttpRequest request, Context context) {
        context.getLogger().log("request: " + request);
       
        List<ReplyPOJO> result = getDAO.getProfileByName(request.getQueryStringParameters().get("firstname"));
        
        return new GetHttpResponse(result);
    }
}