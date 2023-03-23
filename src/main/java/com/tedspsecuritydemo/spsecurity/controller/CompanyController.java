package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.CompanyDto;
import com.tedspsecuritydemo.spsecurity.model.Company;
import com.tedspsecuritydemo.spsecurity.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    // create new
    @PostMapping("api/create-company")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto company){
        log.info("Inside Company Controller");
        // create service
        CompanyDto companyResponseDto = null;
        if(company != null){
            companyResponseDto = companyService.create(company);
            if(companyResponseDto == null){
                return new ResponseEntity<CompanyDto>((CompanyDto) null, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(companyResponseDto ,HttpStatus.OK);
    }

    // get All
    @GetMapping("get")
    public ResponseEntity<List<Company>> getAllCompany(){
        List<Company> company = companyService.getAll();
        return new ResponseEntity<List<Company>>(company, HttpStatus.OK);
    }

    // update - add child company

    // delete


}
