package com.transport.dataservice.dataserviceconfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Data
@Configuration
@RefreshScope
public class DataServiceConfig {
	
	@Value("${spring.security.username}")
	private String username;
	
	@Value("${spring.security.password}")
	private String password;
	
	@Value("${spring.security.role}")
	private String[] roles;
	
	@Value("${query.findAllRequest}")
	private String findRequests;
	
	@Value("${query.findRequestById}")
	private String findReqById;
	
	@Value("${query.deleteRequest}")
	private String deleteRequest;
	
	@Value("${query.saveRequest}")
	private String saveRequest;
	
	@Value("${key.based.enabling}")
	private String key;
	
	@Value("${query.batchUpdate}")
	private String batchQuery;
	
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.transport.dataservice"))
				.build();
	}
	
	
}
