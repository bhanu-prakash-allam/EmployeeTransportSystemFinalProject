package com.transport.dataservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.transport.dataservice.DataServiceApplication;
import com.transport.dataservice.dataservice.DataServiceInterfaceImpl;
import com.transport.dataservice.dataservicecontroller.DataServiceController;
import com.transport.dataservice.exception.RequestNotFoundException;
import com.transport.dataservice.model.DataServiceModel;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = DataServiceApplication.class)
class DataServiceControllerTest {

	
	@Mock
	private DataServiceInterfaceImpl dataService;
	
	@InjectMocks
	private DataServiceController dataController;
	
	
	
	//@SuppressWarnings("unchecked")
	@Test
	public void findAllRequestsTest() {
		List<DataServiceModel> list = dataService.findAllRequests();
		when(dataService.findAllRequests()).thenReturn(list);
		@SuppressWarnings("unused")
		DataServiceModel employeedata =   dataController.findEmpById(1).getBody();
		verify(dataService, times(1)).findAllRequests();
	}
	
	 @Test
	 public void findAllRequestsTestForException() {
		 
		 Exception exception = assertThrows(RequestNotFoundException.class,() ->{
			 when(dataService.findAllRequests()).thenThrow(RequestNotFoundException.class);
			 dataController.findEmplyeeRequests();});
		 
		   assertNotNull(exception);
		   verify(dataService, times(1)).findAllRequests();
		   
		 }
	 @Test
	 public void findEmpByIdTest() {
		 when(dataService.findRequestByEmpId(1)).thenReturn( new DataServiceModel(2,"","",""));
		 @SuppressWarnings("unused")
		DataServiceModel employeedata = dataController.findEmpById(1).getBody();
		verify(dataService,times(1)).findRequestByEmpId(1);
		 
	 }
	   @Test
	  public void findEmpByIdTestForException() {
		  Exception exception = assertThrows(RequestNotFoundException.class,() ->{
			  when(dataService.findRequestByEmpId(1)).thenThrow(RequestNotFoundException.class);
			  dataController.findEmpById(1);});
		  
		    assertNotNull(exception);
		    verify(dataService,times(1)).findRequestByEmpId(1);
		  }
				
		  
		  
	  
			 
		 
	 }
	


