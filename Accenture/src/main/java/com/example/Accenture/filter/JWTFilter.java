package com.example.Accenture.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Accenture.Utility.JWTTokenUtil;
import com.example.Accenture.Utility.MyuserDetails;
import com.example.Accenture.security.MySecurityConfigurer;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	MyuserDetails userdetails ;
	
	@Autowired
	JWTTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String jwtTokenHeader = request.getHeader("Authorization");
		if(jwtTokenHeader != null && !jwtTokenHeader.isEmpty()
				&& jwtTokenHeader.startsWith("Bearer ")) {
			final String jwtToken = jwtTokenHeader.substring(7);
			String userName = jwtTokenUtil.getUserNamefromJWTToken(jwtToken);
			UserDetails userdetail = userdetails.loadUserByUsername(userName);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
					new UsernamePasswordAuthenticationToken(userdetail, null,userdetail.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		filterChain.doFilter(request, response);
	}

}
