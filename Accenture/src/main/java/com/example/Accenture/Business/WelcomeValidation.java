package com.example.Accenture.Business;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WelcomeValidation {

	public String getValidWelcome(String username) {
		if(username != null && !username.trim().isEmpty()
				&& username.length() > 1) {
			return "user Valid";
		}
		return null;
	}
}
