package com.family.tree.lambda.functions.getOps;

public enum GetTypeEnum {
	  
	  BYFIRSTNAME("byfirstname"),
	  BYID("byid"),
	  BYCHILDID("bychildid"),
	  BYPARENTID("byparentid");

	  private final String value;

	  GetTypeEnum(String value) {
	    this.value = value;
	  }

	  public String getValue() {
	    return value;
	  }
	}
