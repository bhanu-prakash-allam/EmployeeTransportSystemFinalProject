package com.transport.dataservice.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.transport.dataservice.entity.EmployeeData;
import com.transport.dataservice.exception.RequestErrorResponse;
import com.transport.dataservice.exception.RequestNotFoundException;
import com.transport.dataservice.repository.DataServiceRepository;


@RestController
@Transactional
public class DataServiceController {

	
	@Autowired
	private DataServiceRepository dataServiceRepository;
	@GetMapping("/requests")
	public ResponseEntity<List<EmployeeData>> findEmplyeeRequests()
	{
		List<EmployeeData> employeeDataList=this.dataServiceRepository.findAll();
		if(employeeDataList.size()>0)
		{
			ResponseEntity<List<EmployeeData>> response=new ResponseEntity<List<EmployeeData>>(employeeDataList,HttpStatus.OK);
			return response;
		}
		else
			throw new RequestNotFoundException("No Employee Record Found");
		
		
	}
	@GetMapping("/requests/{empid}")
	public ResponseEntity<EmployeeData> findEmpById(@PathVariable Integer empid)
	{
		
		EmployeeData employeeData=this.dataServiceRepository.findByEmpId(empid);
		if(employeeData!=null)
		{
		ResponseEntity<EmployeeData> response=new ResponseEntity<EmployeeData>(employeeData,HttpStatus.OK);
		return response;
		}
		
		else
			throw new RequestNotFoundException("No request Available for this Id");
		
			
		
		
	}
	@PostMapping("/save")
	public ResponseEntity<EmployeeData> saveData(@RequestBody EmployeeData employeeData)
	{
		if(employeeData.getDropLocation()!=null||employeeData.getPickupLocation()!=null)
		{
			this.dataServiceRepository.save(employeeData);
			return new ResponseEntity<EmployeeData>(employeeData,HttpStatus.OK);
		}
		else
			throw new RuntimeException("Could not add record!!!");
		
		
	}
	@GetMapping("/edit/status")
	public String editStatus()
	{
		this.dataServiceRepository.modifyStatus();
		return "status changed";
	}
	
	@ExceptionHandler 
	public ResponseEntity<RequestErrorResponse> requestNotFoundHandler(RequestNotFoundException ex) {
		RequestErrorResponse error = new RequestErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());
		ResponseEntity<RequestErrorResponse> response =
										new ResponseEntity<RequestErrorResponse>(error, HttpStatus.NOT_FOUND);
		
		return response;
	}
	@ExceptionHandler  
	public ResponseEntity<RequestErrorResponse> employeeOperationErrorHAndler(Exception ex) {
		
		RequestErrorResponse error = new RequestErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<RequestErrorResponse> response =
										new ResponseEntity<RequestErrorResponse>(error, HttpStatus.NOT_FOUND);
		
		return response;
	}
	
}
