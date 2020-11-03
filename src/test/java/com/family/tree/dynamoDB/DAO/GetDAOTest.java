package com.family.tree.dynamoDB.DAO;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.family.tree.dynamoDB.guice.DynamoDBModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Tests for get operations
 * 
 * @author km056366
 *
 */
public class GetDAOTest {
	
	private GetDAO getDAO;

	
	@Before
	public void begin() {
		final Injector injector = Guice.createInjector(new DynamoDBModule());
		this.getDAO = injector.getInstance(GetDAO.class);
	}
	
	@Test
	public void testGetUserProfileById() {
		getDAO.getProfileById("#user_0860");
	}
	
	@Test
	public void testGetUserProfileByName() {
		getDAO.getProfileByName("Sat");
	}
	
	@Test 
	public void testGetChildrensById() {
		getDAO.getChildrensByParentId("user_123");
	}
	
	@Test
	public void testGetParentsById() {
		getDAO.getChildrensByParentId("user_789");
	}
}
