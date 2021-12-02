package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.DTO.ProfileDTO;
import com.fontys.onlineyearbook.nl.fontys.sem3.config.AuthenticationConfigConstants;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.AuthenticationResponse;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import com.fontys.onlineyearbook.nl.fontys.sem3.service.AuthenticationUserDetailService;
import com.fontys.onlineyearbook.nl.fontys.sem3.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
   private AuthenticationUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody ProfileDTO dto)throws Exception{
        Profile profile = new Profile(dto.getUsername(), dto.getProfileName(), dto.getPwd(), dto.getRole());
        try {
            authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(profile.getUsername(), profile.getPwd())
            );
        }
        //if authentication is not successful and exception is thrown saying incorrect username or password
        catch (BadCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
        //we have to fetch the user details for the generation of a token
        final UserDetails userDetails = userDetailService
                .loadUserByUsername(profile.getUsername());

        //now we generate the jwt
        final String jwt = jwtUtil.generateToken(userDetails);

        //here we pass the jwt in the response entity
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/Guest")
    public ResponseEntity<?> createGuestAuthenticationToken()throws Exception{
        final UserDetails userDetails = userDetailService
                .loadUserByUsername(AuthenticationConfigConstants.GUEST);

        //now we generate the jwt
        final String jwt = jwtUtil.generateToken(userDetails);

        //here we pass the jwt in the response entity
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
