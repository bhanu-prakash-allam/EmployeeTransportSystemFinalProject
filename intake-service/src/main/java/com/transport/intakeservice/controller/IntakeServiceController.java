package com.transport.intakeservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.transport.intakeservice.exception.RequestErrorResponse;
import com.transport.intakeservice.exception.RequestNotFoundException;
import com.transport.intakeservice.model.EmployeeData;
import com.transport.intakeservice.service.IntakeServiceInterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntakeServiceController {

	
	
	@Autowired
	private IntakeServiceInterface intakeServiceInterface;
	
	
	@GetMapping("/employee/requests")
	public ResponseEntity<List<EmployeeData>> findAllRequests()
	{
		
	   return new ResponseEntity<List<EmployeeData>>(this.intakeServiceInterface.getAllRequests(),HttpStatus.OK);
	}
	
	@GetMapping("/request/{eid}")
	public ResponseEntity<EmployeeData> getEmpById(@PathVariable Integer eid)
	{

		return new ResponseEntity<EmployeeData>(this.intakeServiceInterface.findEmployeeById(eid),HttpStatus.OK);
	}
	
	@PostMapping("/save/request")
	public ResponseEntity<EmployeeData> saveRequest(@RequestBody EmployeeData employeeData)
	{
		
		return new ResponseEntity<EmployeeData>(this.intakeServiceInterface.saveEmployeeRequest(employeeData),HttpStatus.OK);
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
	
	
}
