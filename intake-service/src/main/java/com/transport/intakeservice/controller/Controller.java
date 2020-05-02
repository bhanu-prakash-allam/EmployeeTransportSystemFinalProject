package com.transport.intakeservice.controller;

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
import com.transport.intakeservice.model.DataServiceModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Controller {

	
	@Autowired
	private RestTemplate res;
	
	@Value("${dataservice.employeesUrl}")
	private String allEmpUrl;
	
	@Value("${dataservice.employeeIdUrl}")
	private String oneEmpUrl;
	
	@Value("${dataservice.sendrequest}")
	private String sendRequest;
	
	@GetMapping("/employee/requests")
	public List<DataServiceModel> findAllRequests()
	{
		DataServiceModel[] dsm=res.getForObject(allEmpUrl, DataServiceModel[].class);
		List<DataServiceModel> ldsm=Arrays.asList(dsm);
		return ldsm;
	   
	}
	
	@GetMapping("/request/{eid}")
	public DataServiceModel getEmpById(@PathVariable Integer eid)
	{
		
		 Map < String, String > params = new HashMap < String, String > ();
	        params.put("id", Integer.toString(eid));
		 DataServiceModel dsm=res.getForObject(oneEmpUrl, DataServiceModel.class,params);
		 return dsm;
	}
	@PostMapping("/save/employee")
	public void saveRequest(@RequestBody DataServiceModel dataServiceModel)
	{
		this.res.postForObject(sendRequest, dataServiceModel, String.class);
	}
	
	
}
