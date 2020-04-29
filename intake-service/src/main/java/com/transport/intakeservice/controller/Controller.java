package com.transport.intakeservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.transport.intakeservice.model.DataServiceListModel;
import com.transport.intakeservice.model.DataServiceModel;


@RestController
public class Controller {

	
	
	RestTemplate res=new RestTemplate();
	@GetMapping("/employee/requests")
	public List<DataServiceModel> findAllRequests()
	{
		final String dataServiceUrl="http://localhost:3344/requests";
		DataServiceListModel dataServiceListModel=res.getForObject(dataServiceUrl, DataServiceListModel.class);
		List<DataServiceModel> ldsm=dataServiceListModel.getLdsm();
		return ldsm;
	}
	
	@GetMapping("/request/{eid}")
	public DataServiceModel getEmpById(@PathVariable Integer eid)
	{
		final String dataServiceUrl="http://localhost:3344/requests/{id}";
		 Map < String, String > params = new HashMap < String, String > ();
	        params.put("id", Integer.toString(eid));
		 DataServiceModel dsm=res.getForObject(dataServiceUrl, DataServiceModel.class,params);
		 return dsm;
	}
	
	
}
