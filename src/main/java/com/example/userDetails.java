package com.example;

import java.math.BigDecimal;

public class userDetails {
	 
	private String firstName;
    private String lastName;
    private String birthdate;
    private String mail;
    private String gender;
    private String mobile;
    private String address;
    private BigDecimal balance;
	
	public userDetails(String firstName, String lastName, String birthdate, String mail, String gender, String mobile,
			String address, BigDecimal balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.mail = mail;
		this.gender = gender;
		this.mobile = mobile;
		this.address = address;
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
