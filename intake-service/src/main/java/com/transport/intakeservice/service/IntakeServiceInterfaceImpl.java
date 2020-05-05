package com.transport.intakeservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.transport.intakeservice.model.EmployeeData;

@Component
public class IntakeServiceInterfaceImpl implements IntakeServiceInterface {

	@Override
	public List<EmployeeData> findEmployeeRequests(List<EmployeeData> employeeDataList)
	{
		
		List<EmployeeData> filteredEmployeeDataList=employeeDataList.stream().filter(data->data.getStatus().equals("requested")).collect(Collectors.toList());
		
		return filteredEmployeeDataList;
	}

}
