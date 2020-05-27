package com.transport.intakeservice.service;

import java.util.List;

import com.transport.intakeservice.model.DataServiceModel;



public interface IntakeServiceInterface {
	
	public List<DataServiceModel> filterEmployeeRequests(List<DataServiceModel> employeeDataList);
	
	public List<DataServiceModel> getAllRequests();
	
	public DataServiceModel findEmployeeById(Integer eid);
	
	public DataServiceModel saveEmployeeRequest(DataServiceModel employeeData);
	
	public boolean deleteEmployeeRequest(Integer id);

}
