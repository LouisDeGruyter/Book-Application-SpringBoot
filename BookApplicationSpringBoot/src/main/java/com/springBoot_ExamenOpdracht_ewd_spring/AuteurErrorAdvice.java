package com.springBoot_ExamenOpdracht_ewd_spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import exceptions.AuteurNotFoundException;

@RestControllerAdvice
public class AuteurErrorAdvice {
	 @ResponseBody
	    @ExceptionHandler(AuteurNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    String fruitNotFoundHandler(AuteurNotFoundException ex) {
	        return ex.getMessage();
	    }
}
