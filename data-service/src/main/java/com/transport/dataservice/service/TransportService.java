package com.transport.dataservice.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.transport.dataservice.entity.EmployeeData;
import com.transport.dataservice.model.DataServiceModel;
import com.transport.dataservice.repository.DataServiceRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TransportService {
	
	@Autowired
	private DataServiceRepository dataServiceRepository;
	
	public List<DataServiceModel> findAllRequests()
	{
		log.info(Thread.currentThread().getName());
		 List<EmployeeData> emp=this.dataServiceRepository.findAll();
		 for(EmployeeData em:emp)
			{
			 log.info(em.getStatus());	
			}
	
		 List<EmployeeData> lemp=emp.stream().filter(data->data.getStatus().equals("requested")).collect(Collectors.toList());
		 List<DataServiceModel> ldsm=lemp.parallelStream().map(data->{
			 DataServiceModel dsm=new DataServiceModel(data.getEmpId(),data.getPickupLocation(),data.getDropLocation(),data.getStatus());
			 return dsm;
		 }).collect(Collectors.toList());
		 return ldsm;
	}
	public DataServiceModel findEmployee(Integer empid)
	{
		EmployeeData emd= this.dataServiceRepository.findByEmpId(empid);
		log.info(Thread.currentThread().getName());
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
	@Async
	public CompletableFuture<DataServiceModel> saveRequest(DataServiceModel dataServiceModel)
	{
		EmployeeData emd=new EmployeeData();
		emd.setEmpId(dataServiceModel.getEmpId());
		emd.setPickupLocation(dataServiceModel.getPic_upLocation());
		emd.setDropLocation(dataServiceModel.getDrop_Location());
		emd.setStatus(dataServiceModel.getStatus());
		this.dataServiceRepository.save(emd);
<<<<<<< HEAD
		System.out.println(Thread.currentThread().getName());
=======
		log.info(Thread.currentThread().getName());
>>>>>>> c98f4bb7a3d3e6a7e241ad3ecbc2c27d036680ff
		return  CompletableFuture.completedFuture(dataServiceModel);
	}
	
}
