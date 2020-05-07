package com.transport.intakeservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestErrorResponse {

	private String message;
	private Integer errorCode;
	private Long timeStamp;
}
