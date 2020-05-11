package com.transport.intakeservice.service;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.transport.intakeservice.exception.RequestNotFoundException;
import com.transport.intakeservice.model.EmployeeData;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class IntakeServiceInterfaceImpl implements IntakeServiceInterface {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IntakeServiceInterface intakeServiceInterface;
	
	@Value("${dataservice.employeesUrl}")
	private String allEmpUrl;
	
	@Value("${dataservice.employeeIdUrl}")
	private String oneEmpUrl;
	
	@Value("${dataservice.sendrequest}")
	private String sendRequest;
	
	@Value("${dataservice.deleterequest}")
	private String deleteRequest;

	@Override
	
	public List<EmployeeData> findEmployeeRequests(List<EmployeeData> employeeDataList)
	{
		log.info("comming to filter data");
		List<EmployeeData> filteredEmployeeDataList=employeeDataList.stream().filter(data->data.getStatus().equals("requested")).collect(Collectors.toList());
		
		log.info("completed taking data");
		if(filteredEmployeeDataList.size()>0) {
			log.info("returning data");
				return filteredEmployeeDataList;
		}
		else
			log.info("throw exception");
			throw new RequestNotFoundException("No Employee Requested yet");
	}


	@Override
	public List<EmployeeData> getAllRequests() {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		HttpEntity<EmployeeData> request=new HttpEntity<EmployeeData>(header);
		
		EmployeeData[] employeeDataArray= restTemplate.exchange(allEmpUrl, HttpMethod.GET,request, EmployeeData[].class).getBody();
		List<EmployeeData> employeeDataList=Arrays.asList(employeeDataArray);
		List<EmployeeData> filteredEmployeeDataList=this.intakeServiceInterface.findEmployeeRequests(employeeDataList);
		return filteredEmployeeDataList;
		
	}
	
	@Override
	public EmployeeData findEmployeeById(Integer eid) {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		 Map < String, String > params = new HashMap <String,String> ();
	        params.put("id", Integer.toString(eid));
		HttpEntity<EmployeeData> request=new HttpEntity<EmployeeData>(header);
		return restTemplate.exchange(oneEmpUrl, HttpMethod.GET,request, EmployeeData.class,params).getBody();
	}

	
	@Override
	public EmployeeData saveEmployeeRequest(EmployeeData employeeData) {
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		HttpEntity<EmployeeData> request=new HttpEntity<EmployeeData>(employeeData,header);
		return restTemplate.exchange(sendRequest, HttpMethod.POST,request, EmployeeData.class).getBody();
	}


	@Override
	public boolean deleteEmployeeRequest(Integer id) {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		HttpEntity<EmployeeData> request=new HttpEntity<EmployeeData>(header);
		 Map < String, String > params = new HashMap <String,String> ();
	        params.put("empId", Integer.toString(id));
	        
	   return restTemplate.exchange(deleteRequest, HttpMethod.DELETE,request, Boolean.class,params).getBody();
	}
	
	

}
