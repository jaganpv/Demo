package com.example.Accenture;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.Accenture.Business.WelcomeValidation;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class UserValidationTest {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private WelcomeValidation welcomeValidation;
	
	String input = "jagan";
	String output = "Hi jagan Welcome to Accenture";
	
	@Test
	public void checkValiduser() throws Exception {
		Mockito.when(welcomeValidation.getValidWelcome(input)).thenReturn(output);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome/jagan").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockmvc.perform(requestBuilder).andReturn();
		System.out.println("hi"+mvcResult.getResponse().getContentAsString());
		assertEquals(output,mvcResult.getResponse().getContentAsString());
	}
}
