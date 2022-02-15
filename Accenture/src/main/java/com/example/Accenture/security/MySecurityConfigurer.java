package com.example.Accenture.security;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Accenture.Utility.MyuserDetails;
import com.example.Accenture.filter.JWTFilter;

@EnableWebSecurity
public class MySecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyuserDetails userdetails;
	
	@Autowired
	private JWTFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//UserBuilder builder = User.withDefaultPasswordEncoder();
		//auth.inMemoryAuthentication().withUser(builder.username("jagan").password("welcome").roles("Devloper"));
		auth.userDetailsService(userdetails).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity httpsecurity) throws Exception {
		httpsecurity.csrf().disable().authorizeRequests().antMatchers("/authentication").permitAll()
		.anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpsecurity.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	 @Bean 
	 public PasswordEncoder passwordEncoder(){ 
		 return NoOpPasswordEncoder.getInstance(); 
	 }
}
