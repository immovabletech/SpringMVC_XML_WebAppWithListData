package com.tech.immovable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tech.immovable.bean.Employee;
import com.tech.immovable.bean.Response;
import com.tech.immovable.service.EmployeeService;

/**
 * --------------------------------------------------------------------------------------
 * --------------Copyright-(c)-Immovable-Technologies.-All-rights-reserved.--------------
 * ----------------------------User:immovabletech@gmail.com_-----------------------------
 * --------------------------------------------------------------------------------------
 */

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	public List<Employee> listOfEmployee() {
		List<Employee> employeeList = employeeService.getListOfEmployees();
		System.out.println(employeeList.toString());
		System.out.println(employeeList.size());
		return employeeList;
	}
	
	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public @ResponseBody Response createEmployee(@RequestBody Employee employee) {
		Response response = new Response();
		response.setReqStatus(HttpStatus.NOT_ACCEPTABLE);
		try {
			if (employee != null) {
				System.out.println("Validating Employee email ID : " + employee.getEmpId().trim().isEmpty());
				if (employee.getEmail().trim().isEmpty() || employee.getFirstName().trim().isEmpty()) {
					response.setReqMsg("Employee first name & emailID's are mandatory");
					return response;
				}
				if (!employee.getEmail().trim().endsWith("@immovable.com")) {
					response.setReqMsg("Employee Email ID must be with @immovable.com");
					return response;
				}
				return employeeService.createEmployee(employee, response);
			}
		} catch (Exception e) {
			response.setReqMsg("Please provide employee data in json format");
		}
		return response;
	}

}
