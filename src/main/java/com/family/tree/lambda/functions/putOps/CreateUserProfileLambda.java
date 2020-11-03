package com.family.tree.lambda.functions.putOps;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.family.tree.dynamoDB.DAO.CreateTableDAO;
import com.family.tree.dynamoDB.DAO.PutDAO;
import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.dynamoDB.table.mapping.FamilyTreeTablePOJO;
import com.family.tree.dynamoDB.util.GenerateRandomUUID;
import com.family.tree.lambda.functions.putOps.replys.PutHttpResponse;
import com.family.tree.lambda.functions.putOps.requests.PutHttpRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class CreateUserProfileLambda extends FamilyTreeSetters implements RequestHandler<PutHttpRequest, PutHttpResponse> {
	private PutDAO putDAO;
	private CreateTableDAO createTableDAO;
	private GenerateRandomUUID generateRandomUUID;

	
	public CreateUserProfileLambda() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.putDAO = injector.getInstance(PutDAO.class);
		this.createTableDAO = injector.getInstance(CreateTableDAO.class);
		this.generateRandomUUID = injector.getInstance(GenerateRandomUUID.class);
	}
	
    @Override
    public PutHttpResponse handleRequest(PutHttpRequest request, Context context) {
        context.getLogger().log("request: " + request);
        
        createTableDAO.createTable(FamilyTreeTablePOJO.class);

        String userId = generateRandomUUID.buildRandomUUID();
        putDAO.createUserProfile(setUserProfile(request,userId));
        
        return new PutHttpResponse();
    }
}