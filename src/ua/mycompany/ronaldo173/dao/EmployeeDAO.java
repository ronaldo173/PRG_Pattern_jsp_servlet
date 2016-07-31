package ua.mycompany.ronaldo173.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import ua.mycompany.ronaldo173.beans.Employee;
import ua.mycompany.ronaldo173.db.DbUtil;
import ua.mycompany.ronaldo173.exception.ApplicationException;

public class EmployeeDAO {

	DataSource ds;

	public EmployeeDAO(DataSource ds) {
		this.ds = ds;
	}

	public int addEmployee(Employee employee) throws ApplicationException {
		Connection connection = null;
		Statement stmt = null;
		String query = "insert into employee(emp_name, salary, dept_name) values('" + employee.getEmployeeName() + "',"
				+ employee.getSalary() + ",'" + employee.getDeptName() + "')";

		int row = -1;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			row = stmt.executeUpdate(query);
		} catch (SQLException e) {
			ApplicationException exception = new ApplicationException("Unable to insert data: " + e.getMessage(), e);
			throw exception;
		} finally {
			DbUtil.close(stmt);
			DbUtil.close(connection);
		}
		return row;
	}
}