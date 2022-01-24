package com.example.Accenture.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AccentureWelcomeController {

	@GetMapping("/welcome/{name}")
	public String welcomeAccenture(@PathVariable("name") String name) {
		return "Hi "+name+" Welcome to Accenture";
	}
}
