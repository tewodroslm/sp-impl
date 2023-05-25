package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.CompanyDto;
import com.tedspsecuritydemo.spsecurity.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {

    @Mock
    CompanyService companyService;

    @InjectMocks
    CompanyController companyController;

    @Test
    void createCompany_Success(){
        CompanyDto companyDto = CompanyDto.builder()
                .companyName("c1").
                companyLocation("NY").
                build();
        Mockito.when(companyService.create(companyDto)).thenReturn(companyDto);
        ResponseEntity<?> responseEntity = companyController.createCompany(companyDto);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void createCompany_Failure(){
        CompanyDto companyDto = CompanyDto.builder()
                .companyName("c1").
                companyLocation("NY").
                build();
        Mockito.when(companyService.create(companyDto)).thenReturn(null);
        ResponseEntity<?> responseEntity = companyController.createCompany(companyDto);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void getAllCompany_Success(){
        ResponseEntity<?> responseEntity = companyController.getAllCompany();
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

}