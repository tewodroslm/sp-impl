package com.tedspsecuritydemo.spsecurity.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedspsecuritydemo.spsecurity.dto.AuthDto;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthenticateService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;


    @Autowired
    private UsersRepository usersRepository;

    public UserResponse authenticateUser(AuthDto authDto){
        log.info("Inside authenticate Service: {}", authDto);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDto.getUsername(), authDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Authentication: {}, {}", authentication.getPrincipal(), authentication.getAuthorities());

        Optional<Users> userResponse = usersRepository.findByEmail(authDto.getUsername());
        log.info(userResponse.toString());
        if(!userResponse.isEmpty()){
            UserResponse userResponse1 = UserResponse.builder()
                    .uname(userResponse.get().getName())
                    .lname(userResponse.get().getLastName())
                    .email(userResponse.get().getEmail())
                    .uId(userResponse.get().getId())
                    .role(userResponse.get().getRoles().stream().map((x) -> x.getRole()).collect(Collectors.toList()))
                    .build();
            log.info("Full Info : {}", userResponse1);

            if(userResponse1 != null){
                return userResponse1;
            }
        }

        return null;
    }

    public Boolean logout(){
        log.info("Logout User");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(
                    httpServletRequest,
                    httpServletResponse,
                    authentication
            );
            log.info("User Logged Out Successfully");
            return true;
        }
        return false;
    }

}
