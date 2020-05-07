package com.transport.intakeservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.transport.intakeservice.exception.RequestNotFoundException;
import com.transport.intakeservice.model.EmployeeData;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class IntakeServiceInterfaceImpl implements IntakeServiceInterface {
	
	@Autowired
	private RestTemplate res;
	
	@Autowired
	private IntakeServiceInterface intakeServiceInterface;
	
	@Value("${dataservice.employeesUrl}")
	private String allEmpUrl;
	
	@Value("${dataservice.employeeIdUrl}")
	private String oneEmpUrl;
	
	@Value("${dataservice.sendrequest}")
	private String sendRequest;

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

	@HystrixCommand(fallbackMethod ="getRequestsFallBackMethod",ignoreExceptions=RequestNotFoundException.class)
	@Override
	public List<EmployeeData> getAllRequests() {
		
		EmployeeData[] employeeDataArray=res.getForObject(allEmpUrl, EmployeeData[].class);
		List<EmployeeData> employeeDataList=Arrays.asList(employeeDataArray);
		List<EmployeeData> filteredEmployeeDataList=this.intakeServiceInterface.findEmployeeRequests(employeeDataList);
		return filteredEmployeeDataList;
		
	}
	
	@HystrixCommand(fallbackMethod ="getEmpFallBackMethod")
	@Override
	public EmployeeData findEmployeeById(Integer eid) {
		
		 Map < String, String > params = new HashMap <String,String> ();
	        params.put("id", Integer.toString(eid));
	        EmployeeData employeeData=res.getForObject(oneEmpUrl, EmployeeData.class,params);
		 return employeeData;
	}

	@HystrixCommand(fallbackMethod ="postEmpFallBackMethod")
	@Override
	public EmployeeData saveEmployeeRequest(EmployeeData employeeData) {
		log.info("sending data started");
		EmployeeData employeeDataResponse=this.res.postForObject(sendRequest, employeeData, EmployeeData.class);
		log.info("sending data completed");
		return  employeeDataResponse;
	}
	

	public List<EmployeeData> getRequestsFallBackMethod()
	{
		List<EmployeeData> employeeDataList =new ArrayList<EmployeeData>();
		employeeDataList.add(new EmployeeData(0,0000,"No Employee found","Service request failled","default"));
		return employeeDataList;
	}
	
	public EmployeeData getEmpFallBackMethod(Integer eid)
	{
		return new EmployeeData(1,0,"service call failled","default","default");
	}
	public  EmployeeData postEmpFallBackMethod(EmployeeData employeeData)
	{
		return new EmployeeData( 0,0,"service call failld","default","default");
		
	}
	

}
