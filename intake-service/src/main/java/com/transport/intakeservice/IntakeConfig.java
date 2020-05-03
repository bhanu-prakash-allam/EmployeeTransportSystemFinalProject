package com.transport.intakeservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class IntakeConfig {
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.transport.intakeservice"))
				.build().apiInfo(apiInfo());
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo()
	{
		return new ApiInfo(
				"Intake Service",
				"For Employee Transport Service",
				"",
				"",
				"",
				"",
				"");
	}
}
