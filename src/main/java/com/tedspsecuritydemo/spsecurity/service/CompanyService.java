package com.tedspsecuritydemo.spsecurity.service;

import com.tedspsecuritydemo.spsecurity.dto.CompanyDto;
import com.tedspsecuritydemo.spsecurity.model.Company;
import com.tedspsecuritydemo.spsecurity.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    // create repository
    public CompanyDto create(CompanyDto companyDto){
        Company company = new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setLocation(companyDto.getCompanyLocation());
        Company company1 = companyRepository.save(company);
        CompanyDto companyResponseDto = CompanyDto.builder()
                .companyLocation(String.valueOf(company1.getLocation()))
                .companyName(company.getCompanyName())
                .build();
        return companyResponseDto;
    }

    public List<Company> getAll(){
        log.info("Get All Company");
        List<Company> company = companyRepository.findAll();
        return company;
    }

}
