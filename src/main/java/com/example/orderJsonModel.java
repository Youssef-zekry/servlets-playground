package com.example;

public class orderJsonModel {
	int product_id = 0;
	int user_id = 0;
	int item_count = 0;

	public orderJsonModel(int product_id, int user_id, int item_count) {
		this.product_id = product_id;
		this.user_id = user_id;
		this.item_count = item_count;
	}

	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getItem_count() {
		return item_count;
	}
	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}
}
