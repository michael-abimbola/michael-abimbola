package com.fontys.onlineyearbook.nl.fontys.sem3.filter;

import com.fontys.onlineyearbook.nl.fontys.sem3.config.AuthenticationConfigConstants;
import com.fontys.onlineyearbook.nl.fontys.sem3.service.AuthenticationUserDetailService;
import com.fontys.onlineyearbook.nl.fontys.sem3.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);

        String username = null;
        String jwt = null;

        //we check if the header is not null
        //and the header contains the prefix Bearer
        if(authorizationHeader != null && authorizationHeader.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)){
            //the substring 7 gives the token directly and not the "Bearer "
            jwt = authorizationHeader.substring(7);

            //here we extract the username form the jwt token
            username = jwtUtil.extractUsername(jwt);
        }
        //if the username is not null
        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            //we get the user details by using the username to find the user from your database
            UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

            //validate if the jwt and user details are correct or true
            //and if the jwt hasn't expired

            //this operation is normally carried out by spring security but since we only want a new username and password
            //to be created when the jwt is valid therefore we have to take over
            if(jwtUtil.validateToken(jwt, userDetails)){
                //here we create a new username and password token
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        //here we continue the chain
        //by giving the control to the next filter
        filterChain.doFilter(request, response);
    }
}
