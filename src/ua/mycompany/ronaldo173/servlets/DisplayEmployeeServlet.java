package ua.mycompany.ronaldo173.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(this.getClass().getName() + ".." + "get");
		int success = Integer.parseInt(request.getParameter("s"));
		if (success == 1)
			request.setAttribute("result", "Employee Successfully Inserted");
		else
			request.setAttribute("result", "Employee Not Inserted: " + request.getAttribute("error"));
		RequestDispatcher view = request.getRequestDispatcher("displayEmployee.jsp");
		view.forward(request, response);
	}
}