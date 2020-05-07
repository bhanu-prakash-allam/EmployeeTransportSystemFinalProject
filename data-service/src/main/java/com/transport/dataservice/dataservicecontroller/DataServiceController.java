package com.transport.dataservice.dataservicecontroller;

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

import com.transport.dataservice.dataservice.DataServiceInterface;
import com.transport.dataservice.entity.EmployeeData;
import com.transport.dataservice.exception.RequestErrorResponse;
import com.transport.dataservice.exception.RequestNotFoundException;
import com.transport.dataservice.exception.UrlNotFoundException;

@RestController
@Transactional
public class DataServiceController {
	
	@Autowired
    private DataServiceInterface dataServiceInterface;
	
	@GetMapping("/requests")
	public ResponseEntity<List<EmployeeData>> findEmplyeeRequests()
	{
		
		return new ResponseEntity<List<EmployeeData>>(this.dataServiceInterface.findAllRequests(),HttpStatus.OK);
		
	}
	
	@GetMapping("/requests/{empid}")
	public ResponseEntity<EmployeeData> findEmpById(@PathVariable Integer empid)
	{
		
		return new ResponseEntity<EmployeeData>(this.dataServiceInterface.findRequestByEmpId(empid),HttpStatus.OK);		
		
	}
	@PostMapping("/save")
	public ResponseEntity<EmployeeData> saveData(@RequestBody EmployeeData employeeData)
	{
	 
		return new ResponseEntity<EmployeeData>(this.dataServiceInterface.saveEmployeeRequest(employeeData),HttpStatus.OK);
		
	}
	@GetMapping("/edit/status")
	public String editStatus()
	{
		this.dataServiceInterface.changeStatus();
		return "changed";
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
										new ResponseEntity<RequestErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
		return response;
	}
	@ExceptionHandler  
	public ResponseEntity<RequestErrorResponse> UrlErrorHAndler(UrlNotFoundException ex) {
		
		RequestErrorResponse error = new RequestErrorResponse(ex.toString(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<RequestErrorResponse> response =
										new ResponseEntity<RequestErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
		return response;
	}
	
}
