package com.Threado.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "If youre seeing this, your token is valid";
	}
	
	@PutMapping("/register")
	public String test1() {
		return "Anyone can see this";
	}	
}
