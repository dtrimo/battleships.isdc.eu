/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.isdc.internship.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) throws IOException, ServletException {
        response.setStatus(403);
        if (ae instanceof BadCredentialsException){
        	response.getWriter().print("Invalid username - password combination");        	
        } else {
        	response.getWriter().print(ae.getMessage());     
        }
    }
    
}
