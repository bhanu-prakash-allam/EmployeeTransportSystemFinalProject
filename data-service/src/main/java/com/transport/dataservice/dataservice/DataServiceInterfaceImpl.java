package com.transport.dataservice.dataservice;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Service;
import com.transport.dataservice.dataserviceconfig.DataServiceConfig;
import com.transport.dataservice.entity.EmployeeData;
import com.transport.dataservice.exception.RequestNotFoundException;
import com.transport.dataservice.model.DataServiceModel;
import com.transport.dataservice.repository.DataServiceJdbcRepository;
import com.transport.dataservice.repository.DataServiceRepository;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class DataServiceInterfaceImpl implements DataServiceInterface {

	
	@Autowired
	private DataServiceRepository dataServiceRepository;
	
	@Autowired
	private DataServiceJdbcRepository dataServiceJdbcRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataServiceConfig properties;
	
	@Override
	public List<DataServiceModel> findAllRequests() {
		
		
		if(properties.getKey().equals("Y"))
		{
			log.info("Spring DataJpa is executing");
			if(this.dataServiceRepository.findAll().isEmpty())
				throw new RequestNotFoundException("No Employee Record Found");
			else
				return this.dataServiceRepository.findAll().stream().map(data->
					
					 new DataServiceModel(data.getEmpId(),data.getPickupLocation(),data.getDropLocation(),data.getStatus())
					
				).collect(Collectors.toList());
			
		}
		
		else
		{
			log.info("jdbcTemplate is executing");
			if(this.dataServiceJdbcRepository.findAllRequests().isEmpty())
				throw new RequestNotFoundException("No Employee Record Found");
				
			else	
				return this.dataServiceRepository.findAll().stream().map(data->
				
					 new DataServiceModel(data.getEmpId(),data.getPickupLocation(),data.getDropLocation(),data.getStatus())
					
				).collect(Collectors.toList());
		}
			
	}

	@Override
	public DataServiceModel findRequestByEmpId(Integer empId) {
		
		EmployeeData employeeData=null;
		if(properties.getKey().equals("Y"))
		{
			 employeeData=this.dataServiceRepository.findByEmpId(empId);
			
		}
		else
			 employeeData=this.dataServiceJdbcRepository.findRequestByEmpId(empId);	
		
		 if(employeeData!=null)	
			 return new DataServiceModel(employeeData.getEmpId(),employeeData.getPickupLocation(),employeeData.getDropLocation(),employeeData.getStatus());
		else
		{
			log.info("throw exception");
			throw new RequestNotFoundException("No request Available for this Id "+empId);
		}
		
	}

	@Override
	public DataServiceModel saveEmployeeRequest(DataServiceModel dataServiceModel) {
		
		EmployeeData employeeData=new EmployeeData();
		employeeData.setEmpId(dataServiceModel.getEmpId());
		employeeData.setPickupLocation(dataServiceModel.getPicupLocation());
		employeeData.setDropLocation(dataServiceModel.getDropLocation());
		employeeData.setStatus("requested");
		 if(employeeData.getDropLocation()!=null&&employeeData.getPickupLocation()!=null)
			{
				 if(properties.getKey().equals("Y"))
				 {
					 this.dataServiceRepository.save(employeeData);
				 }
				 else
					 this.dataServiceJdbcRepository.saveEmployeeRequest(employeeData);
				 
				return  dataServiceModel;
			}
		 else
			throw new RuntimeException("fill  picup and drop locations!!!");
	}
	

	@Override
	public boolean changeStatus() {
		
		this.dataServiceRepository.modifyStatus();
		return true;
	}

	@Override
	public Boolean deleteRequest(Integer id) {
		
		 if(properties.getKey().equals("Y"))
		 {
			 this.dataServiceRepository.deleteEmployeeRequest(id);
		 }
		 else
			 this.dataServiceJdbcRepository.deleteEmployeeRequest(id);
	 
	 
	 return true;
	}

	@Override
	public void modifyBatchRequests(List<DataServiceModel> requestData) {
		 int batchSize=50;
		 ParameterizedPreparedStatementSetter<DataServiceModel> parameterizedPreparedStatementSetter = (ps,req) -> ps.setInt(1, req.getEmpId());
		jdbcTemplate.batchUpdate(properties.getBatchQuery(), requestData, batchSize, parameterizedPreparedStatementSetter);
		
	}

}
