package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welocome")
public class Helloworld extends HttpServlet{

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html");
		PrintWriter writer=resp.getWriter();
		writer.println("<html><body><h1>");
		writer.println("welcome");
	    writer.println("</h1></body></html>");
		writer.close();
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
				
	}


}
