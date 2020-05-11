package com.transport.dataservice.dataservice;

import java.util.List;

import com.transport.dataservice.entity.EmployeeData;

public interface DataServiceInterface {
	
	public List<EmployeeData> findAllRequests();
	
	public EmployeeData findRequestByEmpId(Integer empId);
	
	public EmployeeData saveEmployeeRequest(EmployeeData employeeData);
	
	public boolean changeStatus();
	
	public  Boolean deleteRequest(Integer id);
	
	public void modifyBatchRequests(List<EmployeeData> requestData);
}
