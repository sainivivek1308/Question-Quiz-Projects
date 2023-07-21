package com.quiz.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@org.springframework.stereotype.Controller
@ControllerAdvice
public class Controller {
	@RequestMapping(value = "/get" , method = RequestMethod.GET)
	@ExceptionHandler(value = Exception.class)
	public String get() {
		return "First";
	}
	
}
