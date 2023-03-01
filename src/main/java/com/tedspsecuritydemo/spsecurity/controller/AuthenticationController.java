package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.AuthDto;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.service.adminService.CreateUserService;
import com.tedspsecuritydemo.spsecurity.service.auth.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    CreateUserService createUserService;

    @Autowired
    AuthenticateService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> logIn(@RequestBody AuthDto authDto) throws Exception {
        log.info("Login method: Login");

        UserResponse auth = authenticationService.authenticateUser(authDto);

        if(auth != null) return new ResponseEntity<UserResponse>( auth,HttpStatus.OK);

       return new ResponseEntity<UserResponse>(HttpStatus.FORBIDDEN);
    }
}
