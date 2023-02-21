package com.tedspsecuritydemo.spsecurity.config;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.Protocol;
import com.tedspsecuritydemo.spsecurity.model.CustomUserDetails;
import com.tedspsecuritydemo.spsecurity.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

        if(StringUtils.isEmpty(username)){
            throw new BadCredentialsException("User is not authenticated.");
        }

        UserDetails customUserDetails = null;
        try{
            customUserDetails = customUserDetailsService.loadUserByEmail(username);
        }catch (UsernameNotFoundException e){
            throw new BadCredentialsException("Invalid user detail");
        }

        return customSuccessfullAuth(authentication, customUserDetails);
    }

    private Authentication customSuccessfullAuth(Authentication authentication, UserDetails customUserDetails) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customUserDetails.getUsername(), authentication.getCredentials(), customUserDetails.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
