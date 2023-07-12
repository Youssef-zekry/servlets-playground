package com.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.zaxxer.hikari.HikariDataSource;

@WebServlet(name = "demo", urlPatterns = "/")

public class demo extends HttpServlet {

	private static HikariDataSource dataSource = new HikariDataSource();

	@Override
	public void init() {
		System.out.println("from init method");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
		dataSource.setUsername("postgres");
		dataSource.setPassword("2111976");
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();

		try (Connection connection = dataSource.getConnection();) {
			user user = gson.fromJson(new InputStreamReader(request.getInputStream()), user.class);

			CallableStatement stmt = connection.prepareCall("{call get_user_details(?,?)}");
			stmt.setString(1, user.getMail());
			stmt.setString(2, user.getPassword());
			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			response.getWriter().write("{\"status\":500} " +"\n" + "Something went wrong while inserting data into database");
		} catch (Exception e) {
			response.getWriter().write("{\"status\":500} " +"\n" + "Something went wrong outside database scope");
		}
	}
}
