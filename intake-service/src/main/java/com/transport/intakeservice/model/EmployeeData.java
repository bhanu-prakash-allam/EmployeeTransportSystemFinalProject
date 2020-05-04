
package com.transport.intakeservice.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {

	
	private Integer Id;
	
	
	private Integer empId;
	
	
	private String pickupLocation;

	
	private String dropLocation;

	
	private String status;

}
