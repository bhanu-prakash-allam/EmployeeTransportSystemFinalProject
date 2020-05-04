package com.transport.intakeservice.service;

import java.util.List;

import com.transport.intakeservice.model.EmployeeData;


public interface IntakeServiceInterf {
	
	public List<EmployeeData> findEmployeeRequests(List<EmployeeData> employeeDataList);
}
