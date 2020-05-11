package com.transport.intakeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public class IntakeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntakeServiceApplication.class, args);
	}

}
