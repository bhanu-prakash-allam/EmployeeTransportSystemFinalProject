package com.transport.dataservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.transport.dataservice.model.DataServiceListModel;
import com.transport.dataservice.model.DataServiceModel;
import com.transport.dataservice.service.TransportService;

@RestController
public class DataServiceController {

	@Autowired
	private TransportService transportService;
	@GetMapping("/requests")
	public ResponseEntity<DataServiceListModel> findEmplyeeRequests()
	{
		List<DataServiceModel> ldsm= this.transportService.findAllRequests();
		DataServiceListModel dslm=new DataServiceListModel();
		dslm.setLdsm(ldsm);
		ResponseEntity<DataServiceListModel> response=new ResponseEntity<DataServiceListModel>(dslm,HttpStatus.OK);
		return response;
		
	}
	@GetMapping("/requests/{empid}")
	public ResponseEntity<DataServiceModel> findEmpById(@PathVariable Integer empid)
	{
		DataServiceModel dsm= this.transportService.findEmployee(empid);
		ResponseEntity<DataServiceModel> response=new ResponseEntity<DataServiceModel>(dsm,HttpStatus.OK);
		
		return response;
	}
	@GetMapping("/requests/status")
	public String editStatus()
	{
		 this.transportService.updateStatus();
		return "status changed";
	}
}
