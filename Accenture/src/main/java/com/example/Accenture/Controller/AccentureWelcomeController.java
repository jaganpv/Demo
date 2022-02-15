package com.example.Accenture.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Accenture.Business.WelcomeValidation;
import com.example.Accenture.Pojo.JWTRequestPojo;
import com.example.Accenture.Pojo.JWTResponsePojo;
import com.example.Accenture.Utility.JWTTokenUtil;
import com.example.Accenture.Utility.MyuserDetails;

@RestController
@RequestMapping("/")
public class AccentureWelcomeController {

	private static final Logger logger = LoggerFactory.getLogger(AccentureWelcomeController.class);
	@Autowired
	private WelcomeValidation validation;
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	@Autowired
	private MyuserDetails myUserDetails;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/authentication")
	public ResponseEntity<?> authenticateUser(@RequestBody JWTRequestPojo jwtRequestPojo) {
		//System.out.println(jwtRequestPojo.getUserName()+jwtRequestPojo.getPassword());
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
		= new UsernamePasswordAuthenticationToken(jwtRequestPojo.getUsrName(), jwtRequestPojo.getPassword());
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		final UserDetails userDetail = myUserDetails.loadUserByUsername(jwtRequestPojo.getUsrName());
		final String token = jwtTokenUtil.generateJWTToken(userDetail);
		return ResponseEntity.ok(new JWTResponsePojo(token));
	}
	
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
