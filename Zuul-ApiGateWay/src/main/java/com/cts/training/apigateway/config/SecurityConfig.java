package com.cts.training.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity  
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Autowired
	ApiConfig properties;
	
	@Autowired
	AuthenticationEntryPoint entryPoint;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
      	.authorizeRequests()
//      	.antMatchers("/data-service/requests").hasRole("ADMIN")
//      	.antMatchers("data-service/delete/request/**").hasRole("ADMIN")
//      	.antMatchers("/data-service/requests/**").hasRole("EMPLOYEE")
      	.antMatchers("cloudfoundryapplication/**").permitAll()
      	.anyRequest().authenticated()
        .and()     
        .httpBasic()
        .authenticationEntryPoint(entryPoint);
      
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser(properties.getUsername())
		.password(encoder.encode(properties.getPassword()))
		.roles(properties.getRoles().get(0));
		auth.inMemoryAuthentication()
		.withUser("bhanu")
		.password(encoder.encode("12345"))
		.roles("ADMIN");
	}
	
}
