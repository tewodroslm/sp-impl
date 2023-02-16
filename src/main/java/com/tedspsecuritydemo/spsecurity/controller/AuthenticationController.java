package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.AuthDto;
import com.tedspsecuritydemo.spsecurity.dto.SignUpDto;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Role;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import com.tedspsecuritydemo.spsecurity.service.CustomUserDetailsService;
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

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) throws Exception {
        log.info("AuthenticationController: registerUser: Entered!");
        log.info("Request Body: ", signUpDto);
        // add check for username exists in a DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
        }
        Role role = Role.builder().role(signUpDto.getRole()).build();
        Set<Role> hs = new HashSet<>();
        hs.add(role);
        Users user = Users.builder()
                .email(signUpDto.getEmail())
                .name(signUpDto.getName())
                .active(signUpDto.getActive())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .lastName(signUpDto.getLastName())
                .roles(hs)
                .build();
        Users u = userRepository.save(user);
        if(u == null){
            throw new Exception("Error creating user");
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody AuthDto authDto) throws Exception {
        log.info("Login method: Login");
        log.debug("Credentials : ",authDto);
//        Authentication authObject = null;
//
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authObject);
//        } catch (BadCredentialsException e){
//            throw new Exception("Invalid credentials");
//        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDto.getUsername(), authDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User signed in successfully",HttpStatus.OK);
    }


}
