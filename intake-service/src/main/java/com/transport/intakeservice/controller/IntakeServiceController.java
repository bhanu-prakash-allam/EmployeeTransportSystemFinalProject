package com.transport.intakeservice.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
	
	@HystrixCommand(fallbackMethod ="getEmpRequestsFallBackMethod",ignoreExceptions =RequestNotFoundException.class)
	@GetMapping("/employee/requests")
	public ResponseEntity<List<EmployeeData>> findAllRequests()
	{
		
	   return new ResponseEntity<List<EmployeeData>>(this.intakeServiceInterface.getAllRequests(),HttpStatus.OK);
	}
	
	@HystrixCommand(fallbackMethod ="getRequestByIdFallBackMethod",ignoreExceptions =RequestNotFoundException.class)
	@GetMapping("/request/{eid}")
	public ResponseEntity<EmployeeData> getEmpById(@PathVariable Integer eid)
	{

		return new ResponseEntity<EmployeeData>(this.intakeServiceInterface.findEmployeeById(eid),HttpStatus.OK);
	}
	
	@HystrixCommand(fallbackMethod ="postEmpFallBackMethod",ignoreExceptions =RuntimeException.class)
	@PostMapping("/save/request")
	public ResponseEntity<EmployeeData> saveRequest(@RequestBody EmployeeData employeeData)
	{
		
		return new ResponseEntity<EmployeeData>(this.intakeServiceInterface.saveEmployeeRequest(employeeData),HttpStatus.OK);
	}
	
	@HystrixCommand(fallbackMethod ="deleteFallBackMethod")
	@DeleteMapping("/delete/request/{empId}")
	public ResponseEntity<Boolean> deleteRequest(@PathVariable Integer empId)
	{
		return new ResponseEntity<Boolean>(this.intakeServiceInterface.deleteEmployeeRequest(empId),HttpStatus.OK);
	}
	
	//Fallback Methods
	
	public ResponseEntity<EmployeeData> postEmpFallBackMethod(EmployeeData employeeData)
	{
		return new ResponseEntity<EmployeeData>(new EmployeeData( 0,0,"service call failld","default","default"),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
		public ResponseEntity<List<EmployeeData>> getEmpRequestsFallBackMethod()
		{
			List<EmployeeData> employeeDataList =new ArrayList<EmployeeData>();
			employeeDataList.add(new EmployeeData(0,0000,"No Employee found","Service request failled","default"));
			return new  ResponseEntity<List<EmployeeData>>(employeeDataList,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	public ResponseEntity<EmployeeData> getRequestByIdFallBackMethod(Integer eid)
	{
		return new ResponseEntity<EmployeeData>(new EmployeeData(1,0,"service call failled","default","default"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<Boolean> deleteFallBackMethod(Integer empId)
	{
		return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
