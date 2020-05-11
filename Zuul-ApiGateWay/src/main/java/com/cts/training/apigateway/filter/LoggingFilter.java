package com.cts.training.apigateway.filter;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

// Registers this class as Zuul Filter
// ZullFilter is an abstract class
// a Zuul filter have object access similar to Servlet
@Component
@Slf4j
public class LoggingFilter extends ZuulFilter{

	HttpServletRequest request;
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		// true : this filter is active
		// false : inactive
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("Request URI -> {}",request.getRequestURI());
		log.info(request.getHeader("Authorization"));
		return request;
	}


	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		// pre : will intercept the incoming request
		// post : will intercept outgoing response
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		// lower value will have higher priority
		return 1;
	}

}
