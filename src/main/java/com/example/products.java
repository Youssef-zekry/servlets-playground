package com.example;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;

@WebServlet(name = "Products", urlPatterns = "/products")
public class products extends HttpServlet {
	private static HikariDataSource dataSource = new HikariDataSource();

	@Override
	public void init() {
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/sample_db");
		dataSource.setUsername("postgres");
		dataSource.setPassword("2111976");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (Connection connection = dataSource.getConnection()) {
			CallableStatement stmt = connection.prepareCall("SELECT * FROM get_all_products()");
			ResultSet rs = stmt.executeQuery();

			List<product> productList = new ArrayList<>();

			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String productDesc = rs.getString("product_desc");
				float productPrice = rs.getFloat("product_price");
				int stock = rs.getInt("stock");

				product product = new product(productId, productName, productDesc, productPrice, stock);
				productList.add(product);
			}
			stmt.close();

			Gson gson = new Gson();
			String json = gson.toJson(productList);
			response.getWriter().write(json);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}