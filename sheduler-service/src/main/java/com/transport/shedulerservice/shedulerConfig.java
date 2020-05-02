package com.transport.shedulerservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class shedulerConfig {
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
}
