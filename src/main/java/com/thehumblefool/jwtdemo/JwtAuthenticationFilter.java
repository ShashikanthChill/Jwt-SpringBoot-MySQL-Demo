/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thehumblefool.jwtdemo;

import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author shash
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Filtering req in Jwt auth filter");

        //get token from request object
        String jwtToken = getJwtTokenFromRequest(request);

        if (jwtToken != null) {
            //get username from jwt token
            String subject = null;
            try {
                subject = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (MalformedJwtException exception) {
                System.out.println("Invalid token!");
                throw exception;
            }

            //find a user with given username
            UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsernameOrEmail(subject);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        filterChain.doFilter(request, response);
    }

    //extracts token from request object's header:: "Authorization"
    private String getJwtTokenFromRequest(HttpServletRequest request) {
        System.out.println("Getting token from req.");
        String authorizationheader = request.getHeader("Authorization");
        if (authorizationheader != null && !authorizationheader.isBlank() && authorizationheader.startsWith("Bearer ")) {
            String token = authorizationheader.substring(7);
            System.out.println("token found: " + token);
            return token;
        }
        System.out.println("token not found. returning null.");
        return null;

    }

}
