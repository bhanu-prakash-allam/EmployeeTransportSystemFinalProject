package com.transport.shedulerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataServiceModel {
	
	private Integer empId;
	private String picupLocation;
	private String dropLocation;
	private String status;
	
}
