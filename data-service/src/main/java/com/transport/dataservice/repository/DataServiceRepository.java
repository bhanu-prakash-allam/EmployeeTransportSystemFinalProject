package com.transport.dataservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.transport.dataservice.entity.EmployeeData;

@Repository
public interface DataServiceRepository extends JpaRepository<EmployeeData,Integer > {

	@Query("SELECT e FROM EmployeeData e WHERE e.empId=?1")
	public EmployeeData findByEmpId(Integer Id);
	
	@Query("FROM EmployeeData e WHERE e.status='requested'")
	public List<EmployeeData> findStatusRequest();
	
	@Query("FROM EmployeeData")
	public List<EmployeeData> findAll();
	
	
}
