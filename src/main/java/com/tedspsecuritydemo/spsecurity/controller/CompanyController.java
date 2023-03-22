package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.CompanyResponseDto;
import com.tedspsecuritydemo.spsecurity.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    // create new
    @PostMapping("/create-company")
    public ResponseEntity<?> creatCompany(@RequestBody String companyName){
        log.info("Inside Company Controller");
        // create service
        CompanyResponseDto companyResponseDto = null;
        if(companyName != null){
            companyResponseDto = companyService.create(companyName);
            if(companyResponseDto == null){
                return new ResponseEntity<>("Error creating company", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Created Successfully \n" + companyResponseDto.toString() ,HttpStatus.OK);
    }

    // get All


    // update - add child company

    // delete


}
