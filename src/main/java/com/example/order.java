package com.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.postgresql.util.PGobject;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;

@WebServlet(name = "order", urlPatterns = "/order")
public class order extends HttpServlet {
private static HikariDataSource dataSource = new HikariDataSource();

	@Override
	public void init() {
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/sample_db");
		dataSource.setUsername("postgres");
		dataSource.setPassword("2111976");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		orderModel input = gson.fromJson(new InputStreamReader(request.getInputStream()), orderModel.class);

		try (Connection connection = dataSource.getConnection()) {
			CallableStatement stmt = connection.prepareCall("{call order_products(?,?)}");
			PGobject orderJson = new PGobject();
     		orderJson.setType("jsonb");
        	orderJson.setValue(gson.toJson(input.getOrderJsonList()));

			stmt.setObject(1, orderJson);
			stmt.setString(2, input.token);
			ResultSet rs = stmt.executeQuery();
			
			userDetails userDetails = null;
			while (rs.next()) {
				userDetails = new userDetails(
						rs.getInt("status_code"),
						rs.getString("message"));
			}
			stmt.close();
			response.getWriter().write(gson.toJson(userDetails));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

