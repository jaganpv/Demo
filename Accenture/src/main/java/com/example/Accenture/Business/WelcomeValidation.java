package com.example.Accenture.Business;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WelcomeValidation {

	@Autowired
	private RestTemplate resttemplate;
	
	public String getValidWelcome(String username) {
		if(username != null && !username.trim().isEmpty()
				&& username.length() > 1) {
			return "user Valid";
		}
		return null;
	}
	
	public String getCurrentWheather(String accessKey,String query) {
		String ouputResponse = "";
		StringBuilder stb= null;
		JSONObject jsonObject = null;
		ouputResponse = resttemplate.getForObject("http://api.weatherstack.com/current?access_key="+accessKey+"&query="+query,String.class);
		if(ouputResponse != null && !ouputResponse.isEmpty()) {
			jsonObject = new JSONObject(ouputResponse);
			JSONObject locationObj = jsonObject.getJSONObject("location");
			JSONObject wheatherObj = jsonObject.getJSONObject("current");
			stb = new StringBuilder();
			 stb.append("Your location is "+locationObj.getString("name")+" \n");
			 stb.append("Your country is "+locationObj.getString("country")+" \n");
			 stb.append("Your region is "+locationObj.getString("region")+" \n");
			 stb.append("Your localtime is "+locationObj.getString("localtime")+" \n");
			 stb.append("Your temperature is "+wheatherObj.getInt("temperature")+" \n");
			 stb.append("Your wind speed is "+wheatherObj.getInt("wind_speed")+" kmph/hr \n");
			 stb.append("Your temperature is "+wheatherObj.getInt("temperature")+" \n");
			 stb.append("Your wind degree is "+wheatherObj.getInt("wind_degree")+" \n");
			 stb.append("Your humidity is "+wheatherObj.getInt("humidity")+" \n");
			
		}
		return stb.toString();
	}
}
