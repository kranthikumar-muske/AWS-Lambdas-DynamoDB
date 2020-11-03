package com.family.tree.dynamoDB.util;

import java.util.UUID;

public class GenerateRandomUUID {
	
	/**
	 * builds random UUID with entitity prefix
	 */
	public String buildRandomUUID(String prefix) {
		return prefix + UUID.randomUUID().toString();
	}
	
	/**
	 * builds random UUID
	 */
	public String buildRandomUUID() {
		return UUID.randomUUID().toString();
	}
}
