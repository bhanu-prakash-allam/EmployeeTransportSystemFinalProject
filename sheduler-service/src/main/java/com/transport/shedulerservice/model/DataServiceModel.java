package com.transport.shedulerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataServiceModel {
	
	private Integer empId;
	private String pic_upLocation;
	private String drop_Location;
	private String status;
	
}
