package com.transport.shedulerservice.service;

import java.util.List;

import com.transport.shedulerservice.model.DataServiceModel;
public interface ShedulerServiceInterface {
	
  
  public List<DataServiceModel> filterEmployeeRequests(List<DataServiceModel> employeeDataList);
  
  public List<DataServiceModel> getAllRequests();
  
  public String autoAproveBatchProcessing();
}
