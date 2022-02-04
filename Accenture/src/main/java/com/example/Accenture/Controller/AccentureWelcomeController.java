package com.example.Accenture.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Accenture.Business.WelcomeValidation;

@RestController
@RequestMapping("/")
public class AccentureWelcomeController {

	private static final Logger logger = LoggerFactory.getLogger(AccentureWelcomeController.class);
	@Autowired
	private WelcomeValidation validation;
	
	@GetMapping("/welcome/{name}")
	public String welcomeAccenture(@PathVariable("name") String name) {
		logger.info("Accenture Welcome Page started");
		String validate = validation.getValidWelcome(name);
		if(validate == null) {
			return "Invalid user";
		}
		logger.info("Accenture Welcome Page eneded");
		return "Hi "+name+" Welcome to Accenture";
	}
	
	@GetMapping("/welcome/getMyWheather")
	public String getCurrentWheather(@RequestParam(name = "access_key") String accesskey
			, @RequestParam(name = "query") String query) {
		System.out.println(" My Wheather -->"+accesskey+" "+query);
		return validation.getCurrentWheather(accesskey, query);
	}
}
