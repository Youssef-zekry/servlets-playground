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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();

		try (Connection connection = dataSource.getConnection();) {
			Statement stmt = connection.createStatement();
			String sql = "select u.user_id, u.first_name, u.last_name, u.birthdate, u.mobile_number, u.mail from users u";
			ResultSet rs = stmt.executeQuery(sql);

			String result = "";
			List<user> users = new ArrayList<>();

			while (rs.next()) {
				user u = new user(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDate(4).toString(),
						rs.getString(5),
						rs.getString(6));
				users.add(u);
				result = result + u.toString();
			}

			JsonArray a = gson.toJsonTree(users).getAsJsonArray();
			resp.getWriter().write(a.toString());

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			resp.getWriter().write("{\"status\":500} " +"\n" + "Something went wrong while getting data from database");
		}
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();

		try (Connection connection = dataSource.getConnection();) {
			user user = gson.fromJson(new InputStreamReader(request.getInputStream()), user.class);

			CallableStatement stmt = connection.prepareCall("{call add_user(?,?,?,?,?)}");
			stmt.setString(1, user.getFirst_name());
			stmt.setString(2, user.getLast_name());
			stmt.setObject(3, user.getBirthdate());
			stmt.setString(4, user.getMobile_number());
			stmt.setString(5, user.getMail());
			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			response.getWriter().write("{\"status\":500} " +"\n" + "Something went wrong while inserting data into database");
		} catch (Exception e) {
			response.getWriter().write("{\"status\":500} " +"\n" + "Something went wrong outside database scope");
		}
	}
}
