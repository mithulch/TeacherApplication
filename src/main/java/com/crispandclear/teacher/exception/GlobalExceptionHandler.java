package com.crispandclear.teacher.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomError idNotFound(IdNotFoundException ex) {
		return new CustomError(new Date(),"Id is not Found, Try with Different Id", ex.getMessage());
	}
	
	@ExceptionHandler(NameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomError nameNotFound(NameNotFoundException ex) {
		return new CustomError(new Date(),"Input Name is not found, try with another name",ex.getMessage());
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomError recordNotFound(RecordNotFoundException ex) {
		return new CustomError(new Date(), "Record is not found.", ex.getMessage());
	}
}
