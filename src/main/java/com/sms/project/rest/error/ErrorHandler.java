package com.sms.project.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ErrorHandler {
	
	//General
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(NotFoundException exc){
		ErrorResponse error = new ErrorResponse(
											HttpStatus.NOT_FOUND.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
		
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc){
		ErrorResponse error = new ErrorResponse(
											HttpStatus.BAD_REQUEST.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException exc){
		ErrorResponse error = new ErrorResponse(
											HttpStatus.BAD_REQUEST.value(),
											"Bad request",
											System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	//User Authorization 
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(IncorrectPasswordException exc){
		ErrorResponse error = new ErrorResponse(
											HttpStatus.FORBIDDEN.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
	}
	
	//Student
	
	
	
}
