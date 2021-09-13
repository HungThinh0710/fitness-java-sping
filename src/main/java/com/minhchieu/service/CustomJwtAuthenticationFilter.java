package com.minhchieu.service;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minhchieu.serviceimpl.CustomAuthenticateService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private CustomAuthenticateService customAuthenticateService;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try{
//            String jwtToken = extractJwtFromRequest(request);
            String authorizationHeader = request.getHeader("Authorization");
            String jwt = null, username = null;
            if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = customAuthenticateService.loadUserByUsername(username);
//
//                    if (jwtTokenUtil.validateToken(jwt, userDetails)) {
//
//                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                                userDetails, null, userDetails.getAuthorities());
//                        usernamePasswordAuthenticationToken
//                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                    }
//                }


                jwtTokenUtil.validateToken(jwt);
//                UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwt), "", jwtTokenUtil.getRolesFromToken(jwt));
                UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwt), "", new ArrayList<>());

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Cannot set the Security Context");
            }
        }catch(ExpiredJwtException ex)
        {
            request.setAttribute("exception", ex);
        }
        catch(BadCredentialsException ex)
        {
            request.setAttribute("exception", ex);
        }
        chain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
