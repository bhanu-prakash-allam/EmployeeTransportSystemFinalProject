package com.transport.intakeservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.transport.intakeservice.exception.RequestNotFoundException;
import com.transport.intakeservice.model.EmployeeData;

import lombok.Data;
@Data
@Component
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
	//Filters the data whose status is requested
	public List<EmployeeData> filterEmployeeRequests(List<EmployeeData> employeeDataList)
	{
		
		List<EmployeeData> filteredEmployeeDataList=employeeDataList.stream().filter(data->data.getStatus().equals("requested")).collect(Collectors.toList());
		
		
		if(filteredEmployeeDataList.size()>0) {
			
				return filteredEmployeeDataList;
		}
		else
			
			throw new RequestNotFoundException("No Employee Requested yet");
	}


	@Override
	public List<EmployeeData> getAllRequests() {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		HttpEntity<EmployeeData> request=new HttpEntity<EmployeeData>(header);
		
		EmployeeData[] employeeDataArray= restTemplate.exchange(allEmpUrl, HttpMethod.GET,request, EmployeeData[].class).getBody();
		List<EmployeeData> employeeDataList=Arrays.asList(employeeDataArray);
		List<EmployeeData> filteredEmployeeDataList=this.intakeServiceInterface.filterEmployeeRequests(employeeDataList);
		return filteredEmployeeDataList;
		
	}
	
	@Override
	public EmployeeData findEmployeeById(Integer eid) {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		 Map < String, String > params = new HashMap <String,String> ();
	        params.put("id", Integer.toString(eid));
		HttpEntity<EmployeeData> request=new HttpEntity<EmployeeData>(header);
		 if(restTemplate.exchange(oneEmpUrl, HttpMethod.GET,request, EmployeeData.class,params).getBody()!=null)
				return restTemplate.exchange(oneEmpUrl, HttpMethod.GET,request, EmployeeData.class,params).getBody();
		 else
				 throw new RequestNotFoundException("No Employee requested on this "+eid);
				
			
	}

	
	@Override
	public EmployeeData saveEmployeeRequest(EmployeeData employeeData) {
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		if(employeeData.getDropLocation()!=null||employeeData.getPickupLocation()!=null)
		{
			HttpEntity<EmployeeData> request=new HttpEntity<EmployeeData>(employeeData,header);
			return restTemplate.exchange(sendRequest, HttpMethod.POST,request, EmployeeData.class).getBody();
		}
		else
			throw new RuntimeException("could not add record..!");
		
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
