package com.fontys.onlineyearbook.nl.fontys.sem3.util;

import com.fontys.onlineyearbook.nl.fontys.sem3.config.AuthenticationConfigConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim (String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(AuthenticationConfigConstants.SECRET.getBytes()).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


    //so we generate a jwt after every successful token
    //after the user details has passed
    public String generateToken(UserDetails userDetails){
        //here we can include the claims we want in the payload of the token
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject){
        //set the claims
        //set the subject which is the person that has been successfully authenticated
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, AuthenticationConfigConstants.SECRET.getBytes()).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        //this extract the user name in the token
        //this gets a user name
        final String username = extractUsername(token);
        //checks if the user name is the same as the user details and if the token is not expired
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
