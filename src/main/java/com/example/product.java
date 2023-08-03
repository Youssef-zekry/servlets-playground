package com.example;

public class product {
	int product_id;
	String product_name;
	String product_desc;
	float product_price;
	int stock;

	public product(int product_id, String product_name, String product_desc, float product_price, int stock) {
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_desc = product_desc;
		this.product_price = product_price;
		this.stock = stock;
	}

	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public float getProduct_price() {
		return product_price;
	}
	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
