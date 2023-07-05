package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgresql.ds.PGSimpleDataSource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet(name = "demo", urlPatterns = "/")

public class demo extends HttpServlet {

	private Gson gson = new Gson();

	// new datasource object
	PGSimpleDataSource dataSource = new PGSimpleDataSource();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		// database connection properties
		dataSource.setServerNames(new String[] { "localhost" });
		dataSource.setPortNumbers(new int[] { 5432 });
		dataSource.setDatabaseName("postgres");
		dataSource.setUser("postgres");
		dataSource.setPassword("2111976");

		Gson gson = new Gson();

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			// out.println(connection);

			Statement stmt = connection.createStatement();
			String sql = "select u.user_id, u.first_name, u.last_name, u.birthdate, u.mobile_number, u.mail from users u";
			// String sql = "select * from users u";
			ResultSet rs = stmt.executeQuery(sql);

			String result = "";

			List<user> users = new ArrayList<>();

			while(rs.next()){
				user u = new user(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3),
					rs.getDate(4), 
					rs.getString(5),
					 rs.getString(6)
				) ;
				
				users.add(u);
				// user u = new user();
				result = result + u.toString();

				// create json object from user
			}

				JsonArray a = gson.toJsonTree(users).getAsJsonArray();

				resp.getWriter().write(a.toString());


		} catch (SQLException e) {
			out.println("my message -> failed to connect to database: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}
}