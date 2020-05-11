package com.transport.intakeservice.intakeserviceconfig;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Data
@RefreshScope
@Configuration
public class IntakeConfig {
	
	@Value("${spring.security.username}")
	private String username;
	
	@Value("${spring.security.password}")
	private String password;
	
	@Value("$spring.security.role}")
	private String[] roles;
	
	@Bean
	//@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.transport.intakeservice"))
				.build();
	}
	
	
}
