package com.tedspsecuritydemo.spsecurity.service;

import com.tedspsecuritydemo.spsecurity.dto.CompanyDto;
import com.tedspsecuritydemo.spsecurity.model.Company;
import com.tedspsecuritydemo.spsecurity.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
