package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.ManagerResponse;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.service.adminService.CreateUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/a1")
public class AdminController {

    @Autowired
    private CreateUserService createUserService;

    @GetMapping("/managers")
    public ResponseEntity<?> getManagers(){
        log.info("Get Agent Users: Manager");
        List<ManagerResponse> users = createUserService.getAllManager();
        log.info(users.toString());
        if(users.isEmpty() || users == null){
            return new ResponseEntity<>(new Exception("No user found"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable String id){
        Users users;
        try{
           users =  createUserService.deleteAgent(id);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
