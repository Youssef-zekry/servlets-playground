package com.example;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "demo" , urlPatterns = "/")

public class demo extends HttpServlet{

	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		user user = new user( 8,"ali", "ahmed", new Date(),"011111111111" , "ali@gmail.com");
		String userJsonString = new Gson().toJson(user);

		PrintWriter out = resp.getWriter();
		out.println(userJsonString);











	// // order order = new order(200,2,new Date(),5,"order from servlet");
		
	// 	String path = req.getSession().getServletContext().getRealPath("index.html");
	// 	String content = new String (Files.readAllBytes(Paths.get(path)));
	// 	resp.getWriter().write(content);

	// 	String i = req.getParameter("num1");
	// 	String j = req.getParameter("num2");
	// 	String x = i+j;
	// 	resp.getWriter().write(x);
	}
}