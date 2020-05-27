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
import com.transport.intakeservice.model.DataServiceModel;
import com.transport.intakeservice.service.IntakeServiceInterface;

@RestController
public class IntakeServiceController {

	
	
	@Autowired
	private IntakeServiceInterface intakeServiceInterface;
	
	@HystrixCommand(fallbackMethod ="getEmpRequestsFallBackMethod",ignoreExceptions =RequestNotFoundException.class)
	@GetMapping("/employee/requests")
	public ResponseEntity<List<DataServiceModel>> findAllRequests()
	{
		
	   return new ResponseEntity<List<DataServiceModel>>(this.intakeServiceInterface.getAllRequests(),HttpStatus.OK);
	}
	
	@HystrixCommand(fallbackMethod ="getRequestByIdFallBackMethod",ignoreExceptions = {RequestNotFoundException.class,Exception.class})
	@GetMapping("/request/{eid}")
	public ResponseEntity<DataServiceModel> getEmpById(@PathVariable Integer eid)
	{

		return new ResponseEntity<DataServiceModel>(this.intakeServiceInterface.findEmployeeById(eid),HttpStatus.OK);
	}
	
	@HystrixCommand(fallbackMethod ="postEmpFallBackMethod",ignoreExceptions =RuntimeException.class)
	@PostMapping("/save/request")
	public ResponseEntity<DataServiceModel> saveRequest(@RequestBody DataServiceModel employeeData)
	{
		
		return new ResponseEntity<DataServiceModel>(this.intakeServiceInterface.saveEmployeeRequest(employeeData),HttpStatus.OK);
	}
	
	@HystrixCommand(fallbackMethod ="deleteFallBackMethod")
	@DeleteMapping("/delete/request/{empId}")
	public ResponseEntity<Boolean> deleteRequest(@PathVariable Integer empId)
	{
		return new ResponseEntity<Boolean>(this.intakeServiceInterface.deleteEmployeeRequest(empId),HttpStatus.OK);
	}
	
	//Fallback Methods
	
	public ResponseEntity<DataServiceModel> postEmpFallBackMethod(DataServiceModel employeeData)
	{
		return new ResponseEntity<DataServiceModel>(new DataServiceModel( 0,"service call failld","try another time","failled"),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
		public ResponseEntity<List<DataServiceModel>> getEmpRequestsFallBackMethod()
		{
			List<DataServiceModel> employeeDataList =new ArrayList<DataServiceModel>();
			employeeDataList.add(new DataServiceModel(0000,"No Employee found","Service request failled","failled"));
			return new  ResponseEntity<List<DataServiceModel>>(employeeDataList,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	public ResponseEntity<DataServiceModel> getRequestByIdFallBackMethod(Integer eid)
	{
		return new ResponseEntity<DataServiceModel>(new DataServiceModel(0,"service call failled","default","default"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<Boolean> deleteFallBackMethod(Integer empId)
	{
		return new ResponseEntity<Boolean>(false,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
