package com.tech.immovable.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.tech.immovable.bean.Employee;
import com.tech.immovable.bean.Response;

/**
 * --------------------------------------------------------------------------------------
 * --------------Copyright-(c)-Immovable-Technologies.-All-rights-reserved.--------------
 * ----------------------------User:immovabletech@gmail.com_-----------------------------
 * --------------------------------------------------------------------------------------
 */

@Repository
public class EmployeeUtil {
	private static List<Employee> employeeList = null;
	private static int empId = 1;

	public List<Employee> getEmployeeList() {
		createEmployeeRepo();
		return employeeList;
	}

	public Response addEmployee(Employee employee, Response response) {
		try {
			createEmployeeRepo();
			for (int i = 0; i < employeeList.size(); i++) {
				if (employeeList.get(i).getEmail().trim().endsWith(employee.getEmail().trim())) {
					response.setReqMsg("Email ID: "+employee.getEmail()+" is taken by other employee, please try with new email ID");
					return response;
				}
			}
			boolean flag = employeeList.add(createEmployee("IT00" + empId++, employee.getFirstName(),
					employee.getLastName(), employee.getEmail(), employee.getDept()));
			if (flag) {
				response.setReqMsg("Employee created successfully with ID: IT00" + (empId - 1));
				response.setReqStatus(HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Employee createEmployee(String empId, String firstName, String lastName, String email, String dept) {
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmail(email);
		emp.setDept(dept);
		System.out.println("New Employee created : " + emp.toString());
		return emp;
	}

	private void createEmployeeRepo() {
		if (employeeList == null) {
			employeeList = new ArrayList<Employee>();
			employeeList.add(createEmployee("IT00" + empId++, "Immovable", "Technology", "immovable.technology@immovable.com", "Immovable Technologies"));
			employeeList.add(createEmployee("IT00" + empId++, "firstname", "lastname", "firstname.lastname@immovable.com", "Immovable Technologies"));
			employeeList.add(createEmployee("IT00" + empId++, "Technology", "Technology", "technology.immovable@immovable.com", "Immovable Technologies"));

		}
	}
}
