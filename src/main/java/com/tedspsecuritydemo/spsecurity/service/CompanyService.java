package com.tedspsecuritydemo.spsecurity.service;

import com.tedspsecuritydemo.spsecurity.dto.CompanyResponseDto;
import com.tedspsecuritydemo.spsecurity.model.Company;
import com.tedspsecuritydemo.spsecurity.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    // create repository
    public CompanyResponseDto create(String name){
        Company company = new Company();
        company.setCompanyName(name);
        Company company1 = companyRepository.save(company);
        CompanyResponseDto companyResponseDto = CompanyResponseDto.builder()
                .companyId(String.valueOf(company1.getId()))
                .companyName(company.getCompanyName())
                .build();
        return companyResponseDto;
    }

}
