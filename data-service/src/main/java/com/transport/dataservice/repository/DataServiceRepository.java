package com.transport.dataservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.transport.dataservice.entity.EmployeeData;


@Repository

public interface DataServiceRepository extends JpaRepository<EmployeeData,Integer > {

	
	
	public EmployeeData findByEmpId(Integer id);
	
	public List<EmployeeData> findAll();
	
	@Modifying
	public void modifyStatus();
	
	@Modifying
	public void deleteEmployeeRequest(Integer empId);
}
