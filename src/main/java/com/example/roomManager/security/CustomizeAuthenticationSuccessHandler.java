package com.example.roomManager.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.net.SyslogOutputStream;


/*
 * This is an Authentication Success handler class for handling what happens after the user is suc
 * successfully logined in the application
 * 
 * 
 * 
 * */
@Component
@PropertySource("classpath:application.properties")
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	//This redirects the authenticated user to a certain page according to the role of the user.
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	        HttpServletResponse response, Authentication authentication)
	        throws IOException, ServletException {
		   boolean isUser = false;
	        boolean isAdmin = false;
	        boolean isAgent = false;
	    //set our response to OK status
	    response.setStatus(HttpServletResponse.SC_OK);
//this check granted authorities against the username
	    
	    Collection<? extends GrantedAuthority> authorities
        = authentication.getAuthorities();
	   
		authorities.forEach(authority -> {
			try {
				 System.out.println("This works"+authority.getAuthority());
				 if(authority.getAuthority().equals("admin")) {
					
						
						redirectStrategy.sendRedirect(request, response, "/home");
					
				} 
				
				else if(authority.getAuthority().equals("user")) {
					
						redirectStrategy.sendRedirect(request, response, "/home");
					
				}
				else {
		            throw new IllegalStateException();
		        }
				
			}
			catch(Exception e) {
				
			}
			
		});
		
	}
	   
	   
	    
	}

	
