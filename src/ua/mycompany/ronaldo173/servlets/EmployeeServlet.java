package ua.mycompany.ronaldo173.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import ua.mycompany.ronaldo173.beans.Employee;
import ua.mycompany.ronaldo173.dao.EmployeeDAO;
import ua.mycompany.ronaldo173.exception.ApplicationException;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/test_prg")
	DataSource ds;

	public EmployeeServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass().getName() + ".." + "get");
		RequestDispatcher view = request.getRequestDispatcher("displayEmployee.jsp");
		view.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass().getName() + ".." + "post");
		// Get Request parameters from form
		String empName = request.getParameter("employeeName");
		String deptName = request.getParameter("deptName");
		double salary = Double.parseDouble(request.getParameter("salary"));

		// Create Employee Object
		Employee employee = new Employee();
		employee.setEmployeeName(empName);
		employee.setSalary(salary);
		employee.setDeptName(deptName);

		// Invoke method in DAO class passing employee object
		EmployeeDAO empDAO = new EmployeeDAO(ds);

		int rows;
		int success = 0;
		try {
			rows = empDAO.addEmployee(employee);
			/*
			 * Using PRG Pattern. Instead of forwarding from doPost() method, we
			 * are doing a redirection to avoid duplicate form submission.
			 */
			if (rows > 0)
				success = 1;
		} catch (ApplicationException e) {
			// Log the error
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}

		response.sendRedirect("displayEmployee.do?s=" + success);
	}
}