package com.transport.intakeservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.transport.intakeservice.model.DataServiceListModel;
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
	
	@GetMapping("/employee/requests")
	public List<DataServiceModel> findAllRequests()
	{
		
		DataServiceListModel dataServiceListModel=res.getForObject(allEmpUrl, DataServiceListModel.class);
		List<DataServiceModel> ldsm=dataServiceListModel.getLdsm();
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
	
	
}
