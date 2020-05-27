package com.transport.dataservice.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.transport.dataservice.dataserviceconfig.DataServiceConfig;
import com.transport.dataservice.entity.EmployeeData;

@Repository
public class DataServiceJdbcRepository {
	
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	
	 @Autowired
	 DataServiceConfig properties;
	
	 public List<EmployeeData> findAllRequests(){
		
		   
		  return jdbcTemplate.query(
		    		properties.getFindRequests(),
		            new BeanPropertyRowMapper<>(EmployeeData.class));
		}
	 
		public EmployeeData findRequestByEmpId(Integer id) {
	   
		    return jdbcTemplate.queryForObject(properties.getFindReqById(), new Object[]{id},new BeanPropertyRowMapper<>(EmployeeData.class));
		}
		
		public void saveEmployeeRequest(EmployeeData employeeData)
		{
	
				jdbcTemplate.update(properties.getSaveRequest(), new Object[] {employeeData.getDropLocation(),
						employeeData.getEmpId(),employeeData.getPickupLocation(),employeeData.getStatus()
				});

		}
		
		public void deleteEmployeeRequest(Integer empId)
		{
			
			jdbcTemplate.update(properties.getDeleteRequest(),empId);
		}
	
}
