package com.example.Accenture.Pojo;

public class JWTResponsePojo {

	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	public JWTResponsePojo(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
