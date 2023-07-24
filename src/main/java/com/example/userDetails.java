package com.example;

import java.math.BigDecimal;
import java.sql.Date;

public class userDetails {
	private int status_code;
	private String message;
	private String firstName;
    private String lastName;
    private Date birthdate;
    private String mail;
    private String gender;
    private String mobile;
	private String address;
    private BigDecimal balance;
	private int userid;
	private String token;
	

	
	public userDetails(int status_code, String message, String firstName, String lastName, Date birthdate, String mail,
			String gender, String mobile, String address, BigDecimal balance, int userid, String token) {
		this.status_code = status_code;
		this.message = message;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.mail = mail;
		this.gender = gender;
		this.mobile = mobile;
		this.address = address;
		this.balance = balance;
		this.userid = userid;
		this.token = token;
	}
	
	 public userDetails(int status_code, String message) {
		this.status_code = status_code;
		this.message = message;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
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

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
