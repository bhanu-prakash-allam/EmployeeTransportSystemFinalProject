package com.transport.shedulerservice.service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.transport.shedulerservice.model.DataServiceModel;
import com.transport.shedulerservice.sdhedulerconfig.ShedulerConfig;



@Service
public class ShedulerServiceInterfaceImpl  implements ShedulerServiceInterface{

	
	@Autowired
	ShedulerConfig properties;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ShedulerServiceInterface shedulerServiceInterface;

	public List<DataServiceModel> filterEmployeeRequests(List<DataServiceModel> employeeDataList)
	{
		
		return employeeDataList.stream().filter(data->data.getStatus().equals("requested")).collect(Collectors.toList());
		
				
	}


	@Override
	public List<DataServiceModel> getAllRequests() {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth(properties.getUsername(), properties.getPassword());
		HttpEntity<DataServiceModel> request=new HttpEntity<DataServiceModel>(header);
		
		DataServiceModel[] employeeDataArray= restTemplate.exchange(properties.getAllEmpUrl(), HttpMethod.GET,request,DataServiceModel[].class).getBody();
		List<DataServiceModel> employeeDataList=Arrays.asList(employeeDataArray);
		return this.shedulerServiceInterface.filterEmployeeRequests(employeeDataList);
		
		
	}

	
	public String autoAproveBatchProcessing() {
		
		List<DataServiceModel> requestedEmployeeDataList=this.shedulerServiceInterface.getAllRequests();
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth(properties.getUsername(), properties.getPassword());
		HttpEntity<List<DataServiceModel>> request=new HttpEntity<List<DataServiceModel>>(requestedEmployeeDataList,header);
		
		return restTemplate.exchange(properties.getBatchUrl(), HttpMethod.POST,request, String.class).getBody();
		
	}
}
