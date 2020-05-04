package com.transport.intakeservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.transport.intakeservice.model.EmployeeData;
import com.transport.intakeservice.service.IntakeServiceInterf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntakeServiceController {

	
	@Autowired
	private RestTemplate res;
	
	@Autowired
	private IntakeServiceInterf intakeServiceInterf;
	
	@Value("${dataservice.employeesUrl}")
	private String allEmpUrl;
	
	@Value("${dataservice.employeeIdUrl}")
	private String oneEmpUrl;
	
	@Value("${dataservice.sendrequest}")
	private String sendRequest;
	
	
	@GetMapping("/employee/requests")
	@HystrixCommand(fallbackMethod ="getFallBackMethod")
	public List<EmployeeData> findAllRequests()
	{
		EmployeeData[] employeeDataArray=res.getForObject(allEmpUrl, EmployeeData[].class);
		List<EmployeeData> employeeDataList=Arrays.asList(employeeDataArray);
		
		List<EmployeeData> filteredEmployeeDataList=this.intakeServiceInterf.findEmployeeRequests(employeeDataList);
		return filteredEmployeeDataList;
	   
	}
	
	@GetMapping("/request/{eid}")
	@HystrixCommand(fallbackMethod ="getEmpFallBackMethod")
	public EmployeeData getEmpById(@PathVariable Integer eid)
	{
		
		 Map < String, String > params = new HashMap < String, String > ();
	        params.put("id", Integer.toString(eid));
	        EmployeeData employeeData=res.getForObject(oneEmpUrl, EmployeeData.class,params);
		 return employeeData;
	}
	@PostMapping("/save/employee")
	@HystrixCommand(fallbackMethod ="postEmpFallBackMethod")
	public void saveRequest(@RequestBody EmployeeData employeeData)
	{
		this.res.postForObject(sendRequest, employeeData,void.class);
		
	}
	
	public List<EmployeeData> getFallBackMethod()
	{
		List<EmployeeData> employeeDataList =new ArrayList<EmployeeData>();
		employeeDataList.add(new EmployeeData(1,0000,"No Employee found","Service request failled","to other service"));
		return employeeDataList;
	}
	
	public EmployeeData getEmpFallBackMethod(@PathVariable Integer eid)
	{
		return new EmployeeData(1,0,"service call failled","","");
	}
	
	public void postEmpFallBackMethod(@RequestBody EmployeeData employeeData)
	{
		
	}
	
	
}
