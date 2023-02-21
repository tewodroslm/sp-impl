package com.tedspsecuritydemo.spsecurity.service.auth;

import com.tedspsecuritydemo.spsecurity.dto.AuthDto;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticateService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public Boolean authenticateUser(AuthDto authDto){
        log.info("Inside authenticate Service: {}", authDto);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDto.getUsername(), authDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Authenticateion: {}", authentication);
        if(authentication != null){
            return true;
        }
        return false;
    }

}
