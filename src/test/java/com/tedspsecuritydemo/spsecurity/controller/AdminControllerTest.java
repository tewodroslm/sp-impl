package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.ManagerResponse;
import com.tedspsecuritydemo.spsecurity.model.Role;
import com.tedspsecuritydemo.spsecurity.service.adminService.CreateUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @InjectMocks
    AdminController adminController;

    @Mock
    CreateUserService userService;

    @Test
    void getBasicUsersTest_NoUser(){
        ResponseEntity<?> responseEntity = adminController.getManagers();
        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    void getManagersTest_Success(){
        ManagerResponse managerResponse = ManagerResponse.builder().id(1)
                .approveLimit(2000)
                .email("abc@gmail.com")
                .name("abc")
                .lastName("l")
                .roles(new ArrayList<Role>())
                .build();
        List<ManagerResponse> users = new ArrayList<>();
        users.add(managerResponse);

        Mockito.when(userService.getAllManager()).thenReturn(users);

        ResponseEntity<?> responseEntity = adminController.getManagers();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void deleteAgent_Success(){
        ResponseEntity<?> responseEntity = adminController.deleteAgent("22");
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


    @Test
    void deleteAgent_Failure(){
        Mockito.when(adminController.deleteAgent(Mockito.anyString()))
                .thenThrow(new Exception("Test exception for unit tests."));

        ResponseEntity<?> responseEntity = adminController.deleteAgent("22");
        assertEquals(304, responseEntity.getStatusCodeValue());
    }



}