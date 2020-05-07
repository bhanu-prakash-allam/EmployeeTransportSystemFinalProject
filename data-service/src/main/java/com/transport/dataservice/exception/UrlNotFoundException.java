package com.transport.dataservice.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
public class UrlNotFoundException extends ResponseEntityExceptionHandler  {
	
	public UrlNotFoundException(String message)
	{
		super();
	}

}
