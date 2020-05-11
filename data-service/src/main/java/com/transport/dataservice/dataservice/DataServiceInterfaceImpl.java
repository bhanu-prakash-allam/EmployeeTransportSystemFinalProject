package com.transport.dataservice.dataservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.transport.dataservice.entity.EmployeeData;
import com.transport.dataservice.exception.RequestNotFoundException;
import com.transport.dataservice.repository.DataServiceRepository;

import lombok.Data;

@Service
@Data
public class DataServiceInterfaceImpl implements DataServiceInterface {

	
	@Autowired
	private DataServiceRepository dataServiceRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//@Value("spring.update")
	String query="UPDATE employee_data SET status='process' WHERE emp_id=?";
	
	@Override
	public List<EmployeeData> findAllRequests() {
		
		if(this.dataServiceRepository.findAll().size()>0)
				return this.dataServiceRepository.findAll();
		else
			throw new RequestNotFoundException("No Employee Record Found");
	}

	@Override
	public EmployeeData findRequestByEmpId(Integer empId) {
		
		EmployeeData employeeData=this.dataServiceRepository.findByEmpId(empId);
		if(employeeData!=null)
		{
		
		return employeeData;
		}
		
		else
			throw new RequestNotFoundException("No request Available for this Id "+empId);
	}

	@Override
	public EmployeeData saveEmployeeRequest(EmployeeData employeeData) {
		
		 this.dataServiceRepository.save(employeeData);
		 if(employeeData.getDropLocation()!=null||employeeData.getPickupLocation()!=null)
			{
			 this.dataServiceRepository.save(employeeData);
			 return employeeData;
			}
		 else
			throw new RuntimeException("Could not add record!!!");
	}
	

	@Override
	public boolean changeStatus() {
		
		this.dataServiceRepository.modifyStatus();
		return true;
	}

	@Override
	public Boolean deleteRequest(Integer id) {
		
	 this.dataServiceRepository.deleteEmployeeRequest(id);
	 
	 return true;
	}

	@Override
	public void modifyBatchRequests(List<EmployeeData> requestData) {
		 int batchSize=50;
		 ParameterizedPreparedStatementSetter<EmployeeData> parameterizedPreparedStatementSetter = (ps,req) -> {ps.setInt(1, req.getEmpId());};
			jdbcTemplate.batchUpdate(query, requestData, batchSize, parameterizedPreparedStatementSetter);
	}

}
