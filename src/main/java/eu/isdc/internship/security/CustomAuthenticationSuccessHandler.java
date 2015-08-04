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
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
//        request.get
//        
//        response.getWriter().print("SUCCESS");
//    }
    
    
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest == null || "XMLHttpRequest".equalsIgnoreCase(request.getHeader("HTTP_X_REQUESTED_WITH"))) {
            clearAuthenticationAttributes(request);
            response.setStatus(200);
            response.getWriter().print("SUCCESS");
        } else{
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            response.setStatus(302);
            response.getWriter().print(savedRequest.getRedirectUrl());
        }
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
    
}
