package ua.mycompany.ronaldo173;

import javax.annotation.Resource;
import javax.sql.DataSource;

import ua.mycompany.ronaldo173.beans.Employee;
import ua.mycompany.ronaldo173.dao.EmployeeDAO;

public class Main {
	@Resource(name = "jdbc/test_prg")
	static DataSource ds;

	public static void main(String[] args) throws Exception {
		EmployeeDAO empDAO = new EmployeeDAO(ds);

		Employee employee = new Employee();
		employee.setEmployeeName("alex");
		employee.setSalary(100500);
		employee.setDeptName("deptName");
		empDAO.addEmployee(employee);
	}

}
