package com.transport.dataservice.dataservicecontroller;

import java.util.List;
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
import com.transport.dataservice.model.DataServiceModel;
import com.transport.dataservice.repository.DataServiceJdbcRepository;

import lombok.extern.slf4j.Slf4j;



@RestController
@Transactional
@Slf4j
public class DataServiceController {
	
	@Autowired
    private DataServiceInterface dataServiceInterface;
	
	@Autowired
	DataServiceJdbcRepository dataServiceJdbcRepository;
	
	@GetMapping("/requests")
	public ResponseEntity<List<DataServiceModel>> findEmplyeeRequests()
	{
		
		log.info("getting all employee requests requested");
		return new ResponseEntity<List<DataServiceModel>>(this.dataServiceInterface.findAllRequests(),HttpStatus.OK);
		
	}
	
	@GetMapping("/requests/{empid}")
	public ResponseEntity<DataServiceModel> findEmpById(@PathVariable Integer empid)
	{
		
		log.info("employee request by id");
		return new ResponseEntity<DataServiceModel>(this.dataServiceInterface.findRequestByEmpId(empid),HttpStatus.OK);		
		
	}
	@PostMapping("/save")
	public ResponseEntity<DataServiceModel> saveData(@RequestBody DataServiceModel dataServiceModel)
	{
	 
		return new ResponseEntity<DataServiceModel>(this.dataServiceInterface.saveEmployeeRequest(dataServiceModel),HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/request/{empId}")
	public Boolean deleteRequest(@PathVariable Integer empId)
	{
		return this.dataServiceInterface.deleteRequest(empId);
	}
	
	@PostMapping("/change/batchRequests")
	public ResponseEntity<String> autoApproveRequest(@RequestBody List<DataServiceModel> modifyData)
	{
		this.dataServiceInterface.modifyBatchRequests(modifyData);
		
		return new ResponseEntity<String>("all requested modified",HttpStatus.OK);
	}
	
	
	
}
