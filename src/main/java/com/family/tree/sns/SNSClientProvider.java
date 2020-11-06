package com.family.tree.sns;

import javax.inject.Provider;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

public class SNSClientProvider implements Provider<SnsClient>{
	
	  @Override
	  public SnsClient get() {
		  return SnsClient.builder().region(Region.US_EAST_2).build();
	  }
}