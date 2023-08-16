package com.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;

@WebServlet(name = "resetPassword", urlPatterns = "/resetPassword")
public class resetPassword extends HttpServlet{
	private static HikariDataSource dataSource = new HikariDataSource();

	@Override
	public void init() {
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/db3");
		dataSource.setUsername("postgres");
		dataSource.setPassword("2111976");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Gson gson = new Gson();
		user input = gson.fromJson(new InputStreamReader(request.getInputStream()), user.class);

		try (Connection connection = dataSource.getConnection()) {
			CallableStatement stmt = connection.prepareCall("{call reset_password(?,?,?)}");
			stmt.setString(1, input.getMail());
			stmt.setInt(2, input.getQuestion_id());
			stmt.setString(3, input.getAnswer());
			ResultSet rs = stmt.executeQuery();

			userDetails userDetails = null;
			while (rs.next()) {
				userDetails = new userDetails(
						rs.getInt("status_code"),
						rs.getString("message"));
			}
			stmt.close();

			response.getWriter().write(gson.toJson(userDetails));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}