package com.transport.intakeservice.service;

import java.util.List;

import com.transport.intakeservice.model.EmployeeData;


public interface IntakeServiceInterface {
	
	public List<EmployeeData> findEmployeeRequests(List<EmployeeData> employeeDataList);
	
	public List<EmployeeData> getAllRequests();
	
	public EmployeeData findEmployeeById(Integer eid);
	
	public EmployeeData saveEmployeeRequest(EmployeeData employeeData);
	
	public boolean deleteEmployeeRequest(Integer id);
}
