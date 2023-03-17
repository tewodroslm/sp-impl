package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.AuthDto;
import com.tedspsecuritydemo.spsecurity.dto.SignUpDto;
import com.tedspsecuritydemo.spsecurity.dto.UserDto;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.service.adminService.CreateUserService;
import com.tedspsecuritydemo.spsecurity.service.auth.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    CreateUserService createUserService;

    @Autowired
    AuthenticateService authenticationService;

    @PostMapping("/signIn")
    public ResponseEntity<UserResponse> signIn(@RequestBody AuthDto authDto) throws Exception {
        log.info("Login method: Login");

        UserResponse auth = authenticationService.authenticateUser(authDto);

        if(auth != null) return new ResponseEntity<UserResponse>( auth,HttpStatus.OK);

       return new ResponseEntity<UserResponse>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) throws Exception {
        log.info("AuthenticationController: registerUser: Entered!");
        log.info("Request Body: ", signUpDto);
         if(signUpDto.getRole() != "USER"){
             return new ResponseEntity<>("Can't register as " + signUpDto.getRole(), HttpStatus.BAD_REQUEST);
         }
        UserDto u = createUserService.createUser(signUpDto);
        if(u == null){
            throw new Exception("Error creating user");
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @GetMapping("/sign-out")
    public ResponseEntity<String> logOut(){
        log.info("Logout Endpoint hit");
        try{
           boolean loggedOut = authenticationService.logout();
           if(loggedOut) return new ResponseEntity<String>("Logged out Success", HttpStatus.OK);
        }catch (Exception e){
//            return new ResponseEntity<String>("Logout fail", HttpStatus.BAD_REQUEST);
            log.error("LogOut Error");
        }
        return new ResponseEntity<String>("Logout fail", HttpStatus.BAD_REQUEST);
    }

}
