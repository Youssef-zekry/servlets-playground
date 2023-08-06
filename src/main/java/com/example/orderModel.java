package com.example;

import java.util.List;

public class orderModel {
	List<orderJsonModel> orderJsonList;
	String token;

	public orderModel(List<orderJsonModel> orderJsonList, String token) {
		this.orderJsonList = orderJsonList;
		this.token = token;
	}

	public List<orderJsonModel> getOrderJsonList() {
		return orderJsonList;
	}

	public void setOrderJsonList(List<orderJsonModel> orderJsonList) {
		this.orderJsonList = orderJsonList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
