package com.restapi.models;

import org.springframework.http.HttpStatus;

public class ApiError {
	
	private HttpStatus httpStatus;
	private String errorMessage;
	
	public ApiError() {
	}
	public ApiError(HttpStatus notFound, String errorMessage) {
		this.httpStatus = notFound;
		this.errorMessage = errorMessage;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
