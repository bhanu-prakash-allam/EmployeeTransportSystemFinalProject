
package com.transport.dataservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeeData {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer empId;
	
	@Column(length = 100)
	private String pickupLocation;

	@Column(length = 100)
	private String dropLocation;

	@Column(length = 100)
	private String status;

}
