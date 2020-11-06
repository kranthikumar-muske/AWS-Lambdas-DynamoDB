package com.family.tree.sns;

import com.google.inject.AbstractModule;

import software.amazon.awssdk.services.sns.SnsClient;

public class SNSModule extends AbstractModule{
	
	@Override
	protected void configure() {
		bind(SnsClient.class).toProvider(SNSClientProvider.class);
		bind(AwsSNSClient.class).to(AwsSNSClientImpl.class);
	}

}
