package com.transport.dataservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class dataServiceConfig {
	
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.transport.dataservice"))
				.build().apiInfo(apiInfo());
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo()
	{
		return new ApiInfo(
				"Data Service",
				"For Employee Transport Service",
				"",
				"",
				"",
				"",
				"");
	}
	
}
