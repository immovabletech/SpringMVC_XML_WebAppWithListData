package com.tech.immovable.service;

import java.util.List;

import com.tech.immovable.bean.Employee;
import com.tech.immovable.bean.Response;

/**
 * --------------------------------------------------------------------------------------
 * --------------Copyright-(c)-Immovable-Technologies.-All-rights-reserved.--------------
 * ----------------------------User:immovabletech@gmail.com_-----------------------------
 * --------------------------------------------------------------------------------------
 */

public interface EmployeeService {
	public List<Employee> getListOfEmployees();

	public Response createEmployee(Employee employee, Response response);

}
