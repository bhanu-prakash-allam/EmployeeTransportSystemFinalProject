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
import com.transport.intakeservice.model.DataServiceModel;

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
	
	@Value("${dataservice.employeesUrl}")
	private String allEmpUrl;
	
	@Value("${dataservice.employeeIdUrl}")
	private String oneEmpUrl;
	
	@Value("${dataservice.sendrequest}")
	private String sendRequest;
	
	
	@GetMapping("/employee/requests")
	@HystrixCommand(fallbackMethod ="getFallBackMethod")
	public List<DataServiceModel> findAllRequests()
	{
		DataServiceModel[] dsm=res.getForObject(allEmpUrl, DataServiceModel[].class);
		List<DataServiceModel> ldsm=Arrays.asList(dsm);
		return ldsm;
	   
	}
	
	@GetMapping("/request/{eid}")
	@HystrixCommand(fallbackMethod ="getEmpFallBackMethod")
	public DataServiceModel getEmpById(@PathVariable Integer eid)
	{
		
		 Map < String, String > params = new HashMap < String, String > ();
	        params.put("id", Integer.toString(eid));
		 DataServiceModel dsm=res.getForObject(oneEmpUrl, DataServiceModel.class,params);
		 return dsm;
	}
	@PostMapping("/save/employee")
	@HystrixCommand(fallbackMethod ="postEmpFallBackMethod")
	public String saveRequest(@RequestBody DataServiceModel dataServiceModel)
	{
		String str=this.res.postForObject(sendRequest, dataServiceModel, String.class);
		return str;
	}
	
	public List<DataServiceModel> getFallBackMethod()
	{
		List<DataServiceModel> ldsm=new ArrayList<DataServiceModel>();
		ldsm.add(new DataServiceModel(0000,"No Employee found","Service request failled","to other service"));
		return ldsm;
	}
	
	public DataServiceModel getEmpFallBackMethod(@PathVariable Integer eid)
	{
		return new DataServiceModel(0,"service call failled","","");
	}
	
	public String postEmpFallBackMethod(@RequestBody DataServiceModel dataServiceModel)
	{
		return "service call failled";
	}
	
	
}
