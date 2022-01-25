package com.example.Accenture.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Accenture.Business.WelcomeValidation;

@RestController
@RequestMapping("/")
public class AccentureWelcomeController {

	@Autowired
	private WelcomeValidation validation;
	
	@GetMapping("/welcome/{name}")
	public String welcomeAccenture(@PathVariable("name") String name) {
		String validate = validation.getValidWelcome(name);
		if(validate == null) {
			return "Invalid user";
		}
		return "Hi "+name+" Welcome to Accenture";
	}
}
