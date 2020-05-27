package com.transport.intakeservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.transport.intakeservice.IntakeServiceApplication;
import com.transport.intakeservice.exception.RequestNotFoundException;
import com.transport.intakeservice.model.DataServiceModel;
import com.transport.intakeservice.service.IntakeServiceInterfaceImpl;


@SpringBootTest(classes = IntakeServiceApplication.class)
class IntakeServiceControllerTest {


	@Mock
	private IntakeServiceInterfaceImpl intakeService;
	
	@InjectMocks
	private IntakeServiceController intakeController;
	
	
	
	//@SuppressWarnings("unchecked")
	@Test
	public void findAllRequestsTest() {
		List<DataServiceModel> list = intakeService.getAllRequests();
	when(intakeService.getAllRequests()).thenReturn(list);
		@SuppressWarnings("unused")
		DataServiceModel employeedata =   intakeController.getEmpById(1).getBody();
		verify(intakeService, times(1)).getAllRequests();
	}
	 @Test
	 public void findAllRequestsTestForException() {
		 
		 Exception exception = assertThrows(RequestNotFoundException.class,() ->{
			 when(intakeService.getAllRequests()).thenThrow(RequestNotFoundException.class);
			 intakeController.findAllRequests();});
		 
		   assertNotNull(exception);
		   verify(intakeService, times(1)).getAllRequests();
		   
		 }
	 @Test
	 public void getEmpByIdTest() {
		 when(intakeService.findEmployeeById(1)).thenReturn(new DataServiceModel(2,"abc","xyz","requested"));
		 @SuppressWarnings("unused")
		DataServiceModel employeedata = intakeController.getEmpById(1).getBody();
		 verify(intakeService,times(1)).findEmployeeById(1);
		 
	 }
	// @SuppressWarnings("unchecked")
	   @Test
	  public void findEmpByIdTestForException() {
		  Exception exception = assertThrows(RequestNotFoundException.class,() ->{
			  when(intakeService.findEmployeeById(1)).thenThrow(RequestNotFoundException.class);
			  intakeController.getEmpById(1);});
		  
		    assertNotNull(exception);
		    verify(intakeService,times(1)).findEmployeeById(1);
		  }
				
		  
		  
	  
			 
		 

}
