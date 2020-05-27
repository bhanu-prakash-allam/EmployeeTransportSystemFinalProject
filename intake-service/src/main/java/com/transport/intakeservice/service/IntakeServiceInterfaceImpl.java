package com.transport.intakeservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.transport.intakeservice.exception.RequestNotFoundException;
import com.transport.intakeservice.intakeserviceconfig.IntakeConfig;
import com.transport.intakeservice.model.DataServiceModel;

@Service
public class IntakeServiceInterfaceImpl implements IntakeServiceInterface {
	

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IntakeServiceInterface intakeServiceInterface;
	
	@Autowired
	private IntakeConfig properties;
	
	@Override
	public List<DataServiceModel> filterEmployeeRequests(List<DataServiceModel> employeeDataList)
	{
		
		List<DataServiceModel> filteredEmployeeDataList=employeeDataList.stream().filter(data->data.getStatus().equals("requested")).collect(Collectors.toList());
		
		
		if(filteredEmployeeDataList.isEmpty()) {
			
			
			throw new RequestNotFoundException("No Employee Requested yet");
		}
		else
			return filteredEmployeeDataList;
			
	}


	@Override
	public List<DataServiceModel> getAllRequests() {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth(properties.getUsername(),properties.getPassword());
		HttpEntity<DataServiceModel> request=new HttpEntity<DataServiceModel>(header);
		
		DataServiceModel[] dataServiceModelArray= restTemplate.exchange(properties.getAllEmpUrl(), HttpMethod.GET,request, DataServiceModel[].class).getBody();
		List<DataServiceModel> employeeDataList=Arrays.asList(dataServiceModelArray);
		
		return this.intakeServiceInterface.filterEmployeeRequests(employeeDataList);
		
	}
	@Override
	public DataServiceModel findEmployeeById(Integer eid) {
		
		 HttpHeaders header=new HttpHeaders();
		 header.setBasicAuth(properties.getUsername(),properties.getPassword());
		 Map < String, String > params = new HashMap <String,String> ();
	     params.put("id", Integer.toString(eid));
		 HttpEntity<DataServiceModel> request=new HttpEntity<DataServiceModel>(header);
		
		 if(restTemplate.exchange(properties.getOneEmpUrl(), HttpMethod.GET,request, DataServiceModel.class,params).getBody()!=null)
				return restTemplate.exchange(properties.getOneEmpUrl(), HttpMethod.GET,request, DataServiceModel.class,params).getBody();
		 else
				 throw new RequestNotFoundException("No Employee requested on this "+eid);
				
			
	}

	
	@Override
	public DataServiceModel saveEmployeeRequest(DataServiceModel employeeData) {
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth(properties.getUsername(),properties.getPassword());
		if(employeeData.getDropLocation()!=null&&employeeData.getPicupLocation()!=null)
		{
			HttpEntity<DataServiceModel> request=new HttpEntity<DataServiceModel>(employeeData,header);
			return restTemplate.exchange(properties.getSendRequest(), HttpMethod.POST,request, DataServiceModel.class).getBody();
		}
		else
			throw new RuntimeException("fill pickup and drop locations..!");
		
	}


	@Override
	public boolean deleteEmployeeRequest(Integer id) {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth(properties.getUsername(),properties.getPassword());
		HttpEntity<DataServiceModel> request=new HttpEntity<DataServiceModel>(header);
		 Map < String, String > params = new HashMap <String,String> ();
	        params.put("empId", Integer.toString(id));
	        
	   return restTemplate.exchange(properties.getDeleteRequest(), HttpMethod.DELETE,request, Boolean.class,params).getBody();
	}

	

}
