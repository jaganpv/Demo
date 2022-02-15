package com.example.Accenture.Utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenUtil {

	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	//@Value("${jwt.secertKey}")
	private static String SECRECT_KEY="champion93JWT";
	
	public String generateJWTToken(UserDetails userDetails) {
		Map<String,Object> claims = new HashMap<String, Object>();
		return doGenerateJWTToken(claims,userDetails.getUsername());
	}
	
	private String doGenerateJWTToken(Map<String,Object> claims,String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, SECRECT_KEY).compact();
		
	}
	
	public String getUserNamefromJWTToken(String token){
		return getUserNamebyClaim(token,Claims::getSubject);
	}
	
	private <T> T getUserNamebyClaim(String token,Function<Claims, T> claimsResolver) {
		Claims tokenClaimsBody = doUserNamefromJWTToken(token);
		return claimsResolver.apply(tokenClaimsBody);
	}
	
	private Claims doUserNamefromJWTToken(String token) {
		return Jwts.parser().setSigningKey(SECRECT_KEY).parseClaimsJws(token).getBody();
	}
	
}
