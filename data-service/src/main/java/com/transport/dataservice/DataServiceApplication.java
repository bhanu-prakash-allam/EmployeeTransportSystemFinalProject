package com.transport.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


<<<<<<< HEAD
@EnableEurekaClient
=======

//@EnableEurekaClient
>>>>>>> c98f4bb7a3d3e6a7e241ad3ecbc2c27d036680ff
@SpringBootApplication
@EnableSwagger2
public class DataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataServiceApplication.class, args);
	}

}
