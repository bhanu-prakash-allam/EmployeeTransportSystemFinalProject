package com.transport.shedulerservice.service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.transport.shedulerservice.model.EmployeeData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShedulerServiceInterfaceImpl  implements ShedulerServiceInterface{

	@Value("${dataservice.url}")
	private String dataServiceUrl;
	
	@Value("${dataservice.employeesUrl}")
	private String allEmpUrl;
	
	@Value("${dataservice.batchUrl}")
	private String batchUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ShedulerServiceInterface shedulerServiceInterface;
	
//	@Scheduled(cron ="${cron.exp}")
//	@Async
//	public void modifyStatus()
//	{
//		String flag=this.restTemplate.getForObject(dataServiceUrl,String.class);
//		log.info(flag);
//	}
//	
	public List<EmployeeData> filterEmployeeRequests(List<EmployeeData> employeeDataList)
	{
		
		List<EmployeeData> filteredEmployeeDataList=employeeDataList.stream().filter(data->data.getStatus().equals("requested")).collect(Collectors.toList());
		
				return filteredEmployeeDataList;
	}


	@Override
	public List<EmployeeData> getAllRequests() {
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		HttpEntity<EmployeeData> request=new HttpEntity<EmployeeData>(header);
		
		EmployeeData[] employeeDataArray= restTemplate.exchange(allEmpUrl, HttpMethod.GET,request, EmployeeData[].class).getBody();
		List<EmployeeData> employeeDataList=Arrays.asList(employeeDataArray);
		List<EmployeeData> filteredEmployeeDataList=this.shedulerServiceInterface.filterEmployeeRequests(employeeDataList);
		return filteredEmployeeDataList;
		
	}

	@Override
	//@Async
	public String autoAproveBatchProcessing() {
		
		List<EmployeeData> requestedEmployeeDataList=this.shedulerServiceInterface.getAllRequests();
		
		HttpHeaders header=new HttpHeaders();
		header.setBasicAuth("bhanu", "123");
		HttpEntity<List<EmployeeData>> request=new HttpEntity<List<EmployeeData>>(requestedEmployeeDataList,header);
		
		return restTemplate.exchange(batchUrl, HttpMethod.POST,request, String.class).getBody();
		
	}
}
