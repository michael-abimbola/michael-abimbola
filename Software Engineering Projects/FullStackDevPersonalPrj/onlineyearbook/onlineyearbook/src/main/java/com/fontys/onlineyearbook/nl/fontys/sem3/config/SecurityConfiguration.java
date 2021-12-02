package com.fontys.onlineyearbook.nl.fontys.sem3.config;

import com.fontys.onlineyearbook.nl.fontys.sem3.filter.JwtRequestFilter;
import com.fontys.onlineyearbook.nl.fontys.sem3.service.AuthenticationUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationUserDetailService authenticationUserDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override protected void configure(HttpSecurity http) throws Exception {
        //we disable the cross site request forgery
        //the csrf is an attack that forces an end user to execute unwanted actions on a web app
        //in which they are currently authenticated
        http.cors().and().csrf().disable().authorizeRequests()
                //here we are permitting everyone to be able to have access to the api call /authenticate
            .antMatchers("/authenticate").permitAll()
                .antMatchers("/authenticate/Guest").permitAll()
                .antMatchers("/temporaryAdmin/**").permitAll()
//            ROLE BASED AUTHENTICATION START
            .antMatchers("/graduatingclass/**").hasAnyAuthority( AuthenticationConfigConstants.ADMIN, AuthenticationConfigConstants.TEACHER, AuthenticationConfigConstants.STUDENT)
            .antMatchers("/graduatingyear/**").hasAnyAuthority(AuthenticationConfigConstants.ADMIN, AuthenticationConfigConstants.GUEST, AuthenticationConfigConstants.STUDENT)
            .antMatchers("/student/**").hasAnyAuthority(AuthenticationConfigConstants.TEACHER, AuthenticationConfigConstants.ADMIN, AuthenticationConfigConstants.STUDENT, AuthenticationConfigConstants.GUEST)
            .antMatchers("/teacher/**").hasAnyAuthority(AuthenticationConfigConstants.ADMIN, AuthenticationConfigConstants.TEACHER, AuthenticationConfigConstants.STUDENT)
            .antMatchers("/ybcm/**").hasAnyAuthority(AuthenticationConfigConstants.TEACHER,AuthenticationConfigConstants.ADMIN)
                .antMatchers("/profile/**").hasAnyAuthority(AuthenticationConfigConstants.TEACHER,AuthenticationConfigConstants.ADMIN, AuthenticationConfigConstants.STUDENT, AuthenticationConfigConstants.YBCM)
            .antMatchers("/image/**/")    .hasAnyAuthority(AuthenticationConfigConstants.ADMIN, AuthenticationConfigConstants.GUEST, AuthenticationConfigConstants.STUDENT, AuthenticationConfigConstants.TEACHER)
//            ROLE BASED AUTHENTICATION END.
                //any other requests will need to be authenticated
            .anyRequest().authenticated()
            .and()
                //here we are also saying jwt should not manage sessions therefore we make it stateless
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //here we then add the newly created filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web ) throws Exception
    {
        web.ignoring().antMatchers( HttpMethod.OPTIONS, "/**" );
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
