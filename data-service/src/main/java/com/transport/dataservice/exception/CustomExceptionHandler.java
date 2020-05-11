package com.transport.dataservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler 
	public ResponseEntity<RequestErrorResponse> requestNotFoundHandler(RequestNotFoundException ex) {
		RequestErrorResponse error = new RequestErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());
		ResponseEntity<RequestErrorResponse> response =
										new ResponseEntity<RequestErrorResponse>(error, HttpStatus.NOT_FOUND);
		
		return response;
	}
	@ExceptionHandler  
	public ResponseEntity<RequestErrorResponse> employeeOperationErrorHAndler(Exception ex) {
		
		RequestErrorResponse error = new RequestErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<RequestErrorResponse> response =
										new ResponseEntity<RequestErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
		return response;
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<RequestErrorResponse> handleMethodArgumentTypeMismatchException(Exception ex){
		RequestErrorResponse error = new RequestErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(),  System.currentTimeMillis());
		return new ResponseEntity<RequestErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//	protected ResponseEntity<RequestErrorResponse> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex){
//		RequestErrorResponse response = new RequestErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(),  System.currentTimeMillis());
//		return new ResponseEntity<RequestErrorResponse>(response,HttpStatus.OK);
//	}
}
