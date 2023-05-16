package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.SignUpDto;
import com.tedspsecuritydemo.spsecurity.dto.UserDto;
import com.tedspsecuritydemo.spsecurity.dto.UserRequest;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.service.adminService.CreateUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        log.info("AuthenticationController: registerUser: Entered!");
        log.info("Request Body: ", signUpDto);
        UserDto u = createUserService.createUser(signUpDto);
        if(u == null){
            return new ResponseEntity<>(new Exception("Error creating user"), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/basic-users")
    public ResponseEntity<?> getBasicUsers(){
        log.info("Get Basic Users: getBasicUsers");
        List<UserResponse> users = createUserService.getAllBasicUsers();
        log.info(users.toString());
        if(users.isEmpty() || users == null){
            return new ResponseEntity<>(new Exception("No user found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
