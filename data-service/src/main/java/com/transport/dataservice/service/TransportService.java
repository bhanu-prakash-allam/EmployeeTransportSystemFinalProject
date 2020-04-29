package com.transport.dataservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.dataservice.entity.EmployeeData;
import com.transport.dataservice.model.DataServiceModel;
import com.transport.dataservice.repository.DataServiceRepository;

@Service
public class TransportService {
	
	@Autowired
	private DataServiceRepository dataServiceRepository;
	public List<DataServiceModel> findAllRequests()
	{
		 List<EmployeeData> emp=this.dataServiceRepository.findAll();
		 List<DataServiceModel> ldsm=emp.stream().map(data->{
			 DataServiceModel dsm=new DataServiceModel(data.getEmpId(),data.getPickupLocation(),data.getDropLocation(),data.getStatus());
			 return dsm;
		 }).collect(Collectors.toList());
		
		 return ldsm;
	}
	public DataServiceModel findEmployee(Integer empid)
	{
		EmployeeData emd= this.dataServiceRepository.findByEmpId(empid);
		DataServiceModel dsm=new DataServiceModel(emd.getEmpId(),emd.getPickupLocation(),emd.getDropLocation(),emd.getStatus());
		return dsm;
	}
	public void updateStatus()
	{
		List<EmployeeData> emp=this.dataServiceRepository.findStatusRequest();
		for(EmployeeData em:emp)
		{
				EmployeeData emd=new EmployeeData(em.getId(),em.getEmpId(),em.getPickupLocation(),em.getDropLocation(),"process");
				this.dataServiceRepository.save(emd);
		}
		
	}
	
}
