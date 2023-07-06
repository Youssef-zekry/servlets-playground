package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

	private static Integer updateCount = 0;

	private static Logger log = Logger.getLogger(demo.class.getName());

	// new datasource object
	PGSimpleDataSource dataSource = new PGSimpleDataSource();

	public demo() {
		// database connection properties
		dataSource.setServerNames(new String[] { "localhost" });
		dataSource.setPortNumbers(new int[] { 5432 });
		dataSource.setDatabaseName("postgres");
		dataSource.setUser("postgres");
		dataSource.setPassword("2111976");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		Connection connection = null;

		try {
			connection = dataSource.getConnection();
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
						rs.getDate(4).toLocalDate(),
						rs.getString(5),
						rs.getString(6));
				users.add(u);
				result = result + u.toString();
			}

			JsonArray a = gson.toJsonTree(users).getAsJsonArray();
			resp.getWriter().write(a.toString());

		} catch (SQLException e) {
			out.println("my message -> failed to connect to database: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		StringBuilder body = new StringBuilder();

		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			body.append(line);
		}
		String json = body.toString();

		log.info(json);

		inputmodel input = gson.fromJson(json, inputmodel.class);

		log.info("=======================================================");
		log.info(gson.fromJson(json, inputmodel.class).toString());
		String sql = "insert into users (first_name, last_name, birthdate, mobile_number, mail ) values (?,?,?,?,?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			log.info("***********************************************************************");
			// log.info(input.getUsers().toString());
			for (user user : input.getUsers()) {
				log.info(user.toString());
				stmt.setString(1, user.getFirst_name());
				stmt.setString(2, user.getLast_name());
				stmt.setDate(3, java.sql.Date.valueOf(user.getBirthdate()));
				stmt.setString(4, user.getMobile_number());
				stmt.setString(5, user.getMail());
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}