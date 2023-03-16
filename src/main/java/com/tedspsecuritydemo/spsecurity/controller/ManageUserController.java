package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.SignUpDto;
import com.tedspsecuritydemo.spsecurity.dto.UserDto;
import com.tedspsecuritydemo.spsecurity.dto.UserRequest;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.service.adminService.CreateUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/m1")
public class ManageUserController {

    @Autowired
    private CreateUserService createUserService;

    @RolesAllowed("ADMIN")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) throws Exception {
        log.info("AuthenticationController: registerUser: Entered!");
        log.info("Request Body: ", signUpDto);
        UserDto u = createUserService.createUser(signUpDto);
        if(u == null){
            throw new Exception("Error creating user");
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }



}
