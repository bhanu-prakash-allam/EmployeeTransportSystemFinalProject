package com.cts.training.apigateway;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	// DriverManagerDataSource
	// ComboPooled DataSource
	// HikariPool DataSource
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/********** JDBC Authentication ***************/

		auth.jdbcAuthentication().dataSource(dataSource);

	}

	// secure the application : define the accessibility rule

	@Override

	protected void configure(HttpSecurity http) throws Exception {

		http
			.cors()// auto configures application to use CrossOrigins
		.and()
			.csrf().disable() 
			.authorizeRequests()
			
				//.antMatchers("/user-service/login").hasRole("USER")
				.antMatchers("/Employee").hasRole("EMPLOYEE")
				.antMatchers("Admin").hasAnyRole("EMPLOYEE","ADMIN")
				// add other antmatcher of plumbing MS
				
		.and()
				.httpBasic();

	}
	
	
	
}
