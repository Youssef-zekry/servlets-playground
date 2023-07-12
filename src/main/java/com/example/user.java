package com.example;

import java.time.LocalDate;

public class user {
	int user_id;
	String first_name;
	String last_name;
	String birthdate;
	String mail;
	String password;
	char gender;
	String mobile;
	String address;

	public user(int user_id, String first_name, String last_name, String birthdate, String mail, String password,
			char gender, String mobile, String address) {
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birthdate = birthdate;
		this.mail = mail;
		this.password = password;
		this.gender = gender;
		this.mobile = mobile;
		this.address = address;
	}

	public user() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getBirthdate() {
		return LocalDate.parse(birthdate);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
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

	@Override
	public String toString() {
		return "user [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", birthdate="
				+ birthdate + ", mail=" + mail + ", password=" + password + ", gender=" + gender + ", mobile=" + mobile
				+ ", address=" + address + "]";
	}


}
