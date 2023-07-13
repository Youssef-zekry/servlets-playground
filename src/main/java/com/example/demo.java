package com.example;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.postgresql.ds.PGSimpleDataSource;

import com.google.gson.Gson;

@WebServlet(name = "demo", urlPatterns = "/")

public class demo extends HttpServlet {

	PGSimpleDataSource dataSource = new PGSimpleDataSource();

	@Override
	public void init() {
		dataSource.setServerNames(new String[] { "localhost" });
		dataSource.setPortNumbers(new int[] { 5432 });
		dataSource.setDatabaseName("sample_db");
		dataSource.setCurrentSchema("public");
		dataSource.setUser("postgres");
		dataSource.setPassword("2111976");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user_id") == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		int user_id = (Integer) session.getAttribute("user_id");

		String userDetails = getUserDetails(user_id);

		response.getWriter().write(userDetails);
	}

	private String getUserDetails(int user_id) {
		try (Connection connection = dataSource.getConnection()) {
			CallableStatement stmt = connection.prepareCall("{call get_user_details(?)}");
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			userDetails userDetails = null;
			while (rs.next()) {
				userDetails = new userDetails(
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getDate("birthdate").toString(),
						rs.getString("mail"),
						rs.getString("gender"),
						rs.getString("mobile"),
						rs.getString("address"),
						rs.getBigDecimal("balance"));
			}
			stmt.close();
			Gson gson = new Gson();
			return gson.toJson(userDetails);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		int user_id = authenticateUser(mail, password);

		HttpSession session = request.getSession(true);
		session.setAttribute("user_id", user_id);
	}

	private int authenticateUser(String mail, String password) {
		try (Connection connection = dataSource.getConnection()) {
			CallableStatement stmt = connection.prepareCall("{call authenticate_user(?,?)}");
			stmt.setString(1, mail);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.println(rsmd.getColumnName(i));
			}
			int user_id = -1;
			if (rs.next()) {
				user_id = rs.getInt("result");
			}
			stmt.close();
			return user_id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
