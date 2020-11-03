package com.family.tree.lambda.functions.putOps;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.family.tree.dynamoDB.DAO.PutDAO;
import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.family.tree.dynamoDB.util.GenerateRandomUUID;
import com.family.tree.lambda.functions.putOps.replys.PutHttpResponse;
import com.family.tree.lambda.functions.putOps.requests.PutSpouseHttpRequest;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class AddSpouseLambda  extends FamilyTreeSetters implements RequestHandler<PutSpouseHttpRequest, PutHttpResponse> {
	private PutDAO putDAO;
	private GenerateRandomUUID generateRandomUUID;

	
	public AddSpouseLambda() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.putDAO = injector.getInstance(PutDAO.class);
		this.generateRandomUUID = injector.getInstance(GenerateRandomUUID.class);
	}
	
    @Override
    public PutHttpResponse handleRequest(PutSpouseHttpRequest request, Context context) {
        context.getLogger().log("request: " + request);
        
        String spouseId = generateRandomUUID.buildRandomUUID();
        String existingPersonId = request.getExistingUserId();
        putDAO.createNewSpouseAndLinkToExistingPerson(setSpouseProfile(request,spouseId), linkSpouseToExistingPerson(spouseId,existingPersonId), linkExistingPersonToSpouse(spouseId,existingPersonId));
        
        return new PutHttpResponse();
    }
}
