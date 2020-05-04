package com.transport.dataservice.dataserviceconfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
public class dataServiceConfig {
	
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.transport.dataservice"))
				.build();
	}
	
	
}
