package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.AuthDto;
import com.tedspsecuritydemo.spsecurity.dto.SignUpDto;
import com.tedspsecuritydemo.spsecurity.dto.UserDto;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Role;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import com.tedspsecuritydemo.spsecurity.service.CustomUserDetailsService;
import com.tedspsecuritydemo.spsecurity.service.adminService.CreateUserService;
import com.tedspsecuritydemo.spsecurity.service.auth.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    CreateUserService createUserService;

    @Autowired
    AuthenticateService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody AuthDto authDto) throws Exception {
        log.info("Login method: Login");

        String auth = authenticationService.authenticateUser(authDto);

        if(auth != null) return new ResponseEntity<String>("User signed in successfully " +auth ,HttpStatus.OK);

       return new ResponseEntity<>("Failed to login", HttpStatus.FORBIDDEN);

    }


}
