package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.CompanyDto;
import com.tedspsecuritydemo.spsecurity.model.Company;
import com.tedspsecuritydemo.spsecurity.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("api/create-company")
    public ResponseEntity<?> createCompany(@RequestBody @Validated CompanyDto company){
        log.info("Inside Company Controller");
        // create service
        CompanyDto companyResponseDto = companyService.create(company);
        if(companyResponseDto == null){
            return new ResponseEntity<>(new Exception("Company not created"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(companyResponseDto ,HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<List<Company>> getAllCompany(){
        List<Company> company = companyService.getAll();
        return new ResponseEntity<List<Company>>(company, HttpStatus.OK);
    }

    // update - add child company

    // delete


}
