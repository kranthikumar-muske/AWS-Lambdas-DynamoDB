package com.family.tree.dynamoDB.table.mapping;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Address {
	@DynamoDBAttribute(attributeName = "HouseNumber")
	private String houseNumber;
	@DynamoDBAttribute(attributeName = "StreetName")
	private String streetName;
	@DynamoDBAttribute(attributeName = "City")
	private String city;
	@DynamoDBAttribute(attributeName = "State")
	private String state;
	@DynamoDBAttribute(attributeName = "PinCode")
	private int pinCode;
	
	public Address() {
	}
	
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
}
