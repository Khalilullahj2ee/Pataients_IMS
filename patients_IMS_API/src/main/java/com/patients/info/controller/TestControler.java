package com.patients.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControler {

	@RequestMapping("/")
	public String home() {
		return "Welcome to MySoft Ltd.";
	} 
	
}
