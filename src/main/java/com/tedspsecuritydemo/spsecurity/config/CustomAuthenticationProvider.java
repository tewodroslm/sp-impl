package com.tedspsecuritydemo.spsecurity.config;

import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import com.tedspsecuritydemo.spsecurity.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

        if(StringUtils.isEmpty(username)){
            throw new BadCredentialsException("User is not authenticated.");
        }

        UserDetails customUserDetails = null;
        // get user email
        Users userEmail = usersRepository.findByName(username).orElseThrow();
        try{
            customUserDetails = customUserDetailsService.loadUserByUsername(userEmail.getEmail());
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
