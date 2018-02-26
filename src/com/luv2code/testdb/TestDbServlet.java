package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//get connection to database
		String user = "springstudent";
		String password = "springStudent123+";
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?userSSL=false";
		
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			PrintWriter out = response.getWriter();
			out.print("Connecting to database: " + jdbcUrl );
			Class.forName(driver);
			
			Connection conn = (Connection) DriverManager.getConnection(jdbcUrl,user,password);
			
			out.println("Connection Success!");
			
			}catch (Exception exp) {
				exp.printStackTrace();
				throw new ServletException(exp);
			}
	}

}
