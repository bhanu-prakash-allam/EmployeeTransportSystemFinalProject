package com.transport.dataservice.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.transport.dataservice.entity.EmployeeData;
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
		ResponseEntity<List<EmployeeData>> response=new ResponseEntity<List<EmployeeData>>(employeeDataList,HttpStatus.OK);
		return response;
		
	}
	@GetMapping("/requests/{empid}")
	public ResponseEntity<EmployeeData> findEmpById(@PathVariable Integer empid)
	{
		EmployeeData employeeData=this.dataServiceRepository.findByEmpId(empid);
		ResponseEntity<EmployeeData> response=new ResponseEntity<EmployeeData>(employeeData,HttpStatus.OK);
		
		return response;
	}
	@PostMapping("/save")
	public void SaveData(@RequestBody EmployeeData employeeData)
	{
		this.dataServiceRepository.save(employeeData);
		
	}
	@GetMapping("/requests/status")
	public String editStatus()
	{
		this.dataServiceRepository.modifyStatus();
		return "status changed";
	}
}
