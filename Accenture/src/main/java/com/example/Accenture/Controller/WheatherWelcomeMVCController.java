package com.example.Accenture.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Accenture.Business.WelcomeValidation;

@Controller
public class WheatherWelcomeMVCController {

	@Autowired
	WelcomeValidation validation;
	
	@RequestMapping(value="/getMyWheather",method = RequestMethod.GET)
	public String getCurrentWheather(ModelMap map,@RequestParam(name = "access_key") String accesskey
			, @RequestParam(name = "query") String query) {
		map.addAttribute("whetherReport",validation.getCurrentWheather(accesskey, query));
		return "MyWheatherToday";
	}
	
}
