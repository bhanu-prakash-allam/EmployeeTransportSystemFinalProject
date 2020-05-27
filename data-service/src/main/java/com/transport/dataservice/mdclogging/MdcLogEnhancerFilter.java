package com.transport.dataservice.mdclogging;

import java.io.IOException;

import javax.servlet.*;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class MdcLogEnhancerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		ServletUriComponentsBuilder builder=ServletUriComponentsBuilder.fromCurrentRequestUri();
			if(builder.toUriString().endsWith("/requests"))
			{
			String token =" data-service-findAllRequests-"+System.currentTimeMillis();
			MDC.put("transactionId", token);
			}
			else if(builder.toUriString().endsWith("/save"))
			{
				String token =" data-service-saveRequest-"+System.currentTimeMillis();
				MDC.put("userId", token);
			}
			else if(builder.toUriString().matches("(.*)delete(.*)"))
			{
				String token =" data-service-deleteRequest-"+System.currentTimeMillis();
				MDC.put("userId", token);
			}
				
			
	        chain.doFilter(request, response);
		
	}
	
}
