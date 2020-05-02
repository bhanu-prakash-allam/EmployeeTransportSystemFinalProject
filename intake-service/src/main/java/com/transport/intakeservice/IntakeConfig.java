package com.transport.intakeservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IntakeConfig {
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
}
