package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// public class demo implements Servlet{

// 	@Override
// 	public void destroy() {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public ServletConfig getServletConfig() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public String getServletInfo() {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}

// 	@Override
// 	public void init(ServletConfig arg0) throws ServletException {
// 		// TODO Auto-generated method stub
		
// 	}

// 	@Override
// 	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
// 		// TODO Auto-generated method stub
// 		PrintWriter out = arg1.getWriter();
// 		out.println("welcome");
// 	}
	
// }


@WebServlet(name = "demo" , urlPatterns = "/")

public class demo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// String i = req.getParameter("num1");
		// String j = req.getParameter("num2");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");
		out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
		out.println("</body></html>");
		out.close();
		// String x = i+j;
		// System.out.println(x);
		// resp.getWriter().write(x);
	}
}