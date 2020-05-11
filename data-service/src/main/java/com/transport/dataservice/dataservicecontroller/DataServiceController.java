package com.transport.dataservice.dataservicecontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.transport.dataservice.dataservice.DataServiceInterface;
import com.transport.dataservice.entity.EmployeeData;

import lombok.extern.slf4j.Slf4j;



@RestController
@Transactional
@Slf4j
public class DataServiceController {
	
	@Autowired
    private DataServiceInterface dataServiceInterface;
	
	@GetMapping("/requests")
	public ResponseEntity<List<EmployeeData>> findEmplyeeRequests()
	{
		
		log.info("user requested"+Thread.currentThread().getName());
		return new ResponseEntity<List<EmployeeData>>(this.dataServiceInterface.findAllRequests(),HttpStatus.OK);
		
	}
	
	@GetMapping("/requests/{empid}")
	public ResponseEntity<EmployeeData> findEmpById(@PathVariable Integer empid)
	{
		
		//log.info("user requested"+Thread.currentThread().getName()+"the user is "+request.getHeader("Autherization"));
		return new ResponseEntity<EmployeeData>(this.dataServiceInterface.findRequestByEmpId(empid),HttpStatus.OK);		
		
	}
	@PostMapping("/save")
	public ResponseEntity<EmployeeData> saveData(@RequestBody EmployeeData employeeData)
	{
	 
		return new ResponseEntity<EmployeeData>(this.dataServiceInterface.saveEmployeeRequest(employeeData),HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/request/{empId}")
	public Boolean deleteRequest(@PathVariable Integer empId)
	{
		return this.dataServiceInterface.deleteRequest(empId);
	}
	
	@PostMapping("/change/batchRequests")
	public ResponseEntity<String> autoApproveRequest(@RequestBody List<EmployeeData> modifyData)
	{
		this.dataServiceInterface.modifyBatchRequests(modifyData);
		
		return new ResponseEntity<String>("all requested modified",HttpStatus.OK);
	}
	@GetMapping("/edit/status")
	public String editStatus()
	{
		this.dataServiceInterface.changeStatus();
		return "changed";
	}
	
	
	
	
}
