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
		return "Ola amigo";
	}
	
	@PutMapping("/register")
	public String test1() {
		System.out.println("ourch");
		return "register Controller hit";
	}	
}
