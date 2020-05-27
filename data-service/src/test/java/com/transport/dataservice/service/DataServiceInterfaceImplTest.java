package com.transport.dataservice.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import com.transport.dataservice.DataServiceApplication;
import com.transport.dataservice.dataservice.DataServiceInterfaceImpl;
import com.transport.dataservice.entity.EmployeeData;
import com.transport.dataservice.exception.RequestNotFoundException;
import com.transport.dataservice.model.DataServiceModel;
import com.transport.dataservice.repository.DataServiceRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


	@SpringBootTest(classes = DataServiceApplication.class)
class DataServiceInterfaceImplTest {
		@InjectMocks
	DataServiceInterfaceImpl dataService;
		
		@Mock
		DataServiceRepository dataRepo;

		private Integer empId;
		@Test
		public void findAllRequests() throws Exception {
			List<EmployeeData>  employeedata = new ArrayList<>();
			employeedata.add(new EmployeeData(1,4,"abcdxyz","elcot","requested"));
			Mockito
			.when(dataRepo.findByEmpId(1))
			.thenReturn((EmployeeData) employeedata);
		List<DataServiceModel> retrievedEmployeeDatas = dataService.findAllRequests();
		assertEquals(1, retrievedEmployeeDatas.size());
			
		
		}
		@Test
		public  void testforRequestNotFoundException() throws Exception {
			
			 Exception exception = assertThrows(RequestNotFoundException.class,() ->{
				 when(dataService.findAllRequests()).thenThrow(RequestNotFoundException.class);
				 dataService.findAllRequests();});
			 
			   assertNotNull(exception);
			   verify(dataService, times(1)).findAllRequests();
		}
		@Test
		public void findRequestByEmpId() throws Exception {
			List<EmployeeData>  employeedata = new ArrayList<>();
			employeedata.add(new EmployeeData(1,4,"abcdxyz","elcot","requested"));
			Mockito
			.when(dataRepo.findByEmpId(1))
			.thenReturn((EmployeeData) employeedata);
		@SuppressWarnings("unused")
		DataServiceModel retrievedEmployeeDatas = dataService.findRequestByEmpId(empId);
		assertEquals(1, employeedata);
			
		}
		  @Test
		  public void findEmpByIdTestForException() {
			  Exception exception = assertThrows(RequestNotFoundException.class,() ->{
				  when(dataService.findRequestByEmpId(1)).thenThrow(RequestNotFoundException.class);
				  dataRepo.findById(1);});
			  
			    assertNotNull(exception);
			    verify(dataService,times(1)).findRequestByEmpId(1);
			  }
		
		@Test
		public void  saveEmployeeRequest() throws Exception {

        }
	}
	
	
	
	
	

