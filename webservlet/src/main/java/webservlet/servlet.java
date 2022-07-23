package webservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet. annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get")

public class servlet extends HttpServlet{
public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
   PrintWriter pw=res.getWriter();
   
   String name=req.getParameter("name");
   String password=req.getParameter("password");
   pw.println(name);
   pw.println(password);
   pw.close();
   

	
}
}