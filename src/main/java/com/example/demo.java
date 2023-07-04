package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "demo" , urlPatterns = "/")

public class demo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getSession().getServletContext().getRealPath("index.html");
		String content = new String (Files.readAllBytes(Paths.get(path)));
		resp.getWriter().write(content);

		String i = req.getParameter("num1");
		String j = req.getParameter("num2");
		String x = i+j;
		resp.getWriter().write(x);
	}
}