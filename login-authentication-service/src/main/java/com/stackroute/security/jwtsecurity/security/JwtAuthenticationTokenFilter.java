package com.stackroute.security.jwtsecurity.security;

import com.stackroute.security.jwtsecurity.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {


    public JwtAuthenticationTokenFilter() {
        super("/rest/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)  {

        String header = httpServletRequest.getHeader("Authorization");
        System.out.println("in /rest authHeader is  = " + header);

        // Handling CORS in filtered requests start..//

        if (CorsUtils.isPreFlightRequest(httpServletRequest)) {

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

           httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
           httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,HEAD,POST");
           httpServletResponse.setHeader("Access-Control-Allow-Headers","authorization");
           httpServletResponse.setHeader("Allow","GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");

            System.out.println("here for handling no-cors, OPTIONS Requests ");

            return null;
        }
        // Handling CORS in filtered requests end..//


        if (header == null || !header.startsWith("Token ")) {

            throw new RuntimeException("JWT Token is missing");
        }

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);





    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
