package com.transport.shedulerservice.service;

import java.util.List;

import com.transport.shedulerservice.model.EmployeeData;

public interface ShedulerServiceInterface {
	
 // public void modifyStatus();
  
  public List<EmployeeData> filterEmployeeRequests(List<EmployeeData> employeeDataList);
  
  public List<EmployeeData> getAllRequests();
  
  public String autoAproveBatchProcessing();
}
