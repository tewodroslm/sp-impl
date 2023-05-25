package com.tedspsecuritydemo.spsecurity.controller;

import com.tedspsecuritydemo.spsecurity.dto.SignUpDto;
import com.tedspsecuritydemo.spsecurity.dto.UserDto;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Role;
import com.tedspsecuritydemo.spsecurity.service.adminService.CreateUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ManageUserControllerTest {

    @Mock
    CreateUserService createUserService;

    @InjectMocks
    ManageUserController controller;

    @Test
    void registerUser_Success(){
        UserDto userDto = UserDto.builder().role(new HashSet<>()).uname("abc")
                .build();
        SignUpDto signUpDto = new SignUpDto();
        Mockito.when(createUserService.createUser(signUpDto)).thenReturn(userDto);
        ResponseEntity<?> responseEntity = controller.registerUser(signUpDto);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void registerUser_Failure(){
        SignUpDto signUpDto = new SignUpDto();
        ResponseEntity<?> responseEntity = controller.registerUser(signUpDto);
        assertEquals(304, responseEntity.getStatusCodeValue());
    }

    @Test
    void getBasicUsers_Success(){
        UserResponse userResponse = UserResponse.builder()
                .id(1)
                .email("abc@gmail.com")
                .lastName("l")
                .name("abc")
                .roles(new ArrayList<>())
                .build();

        List<UserResponse> userResponseList = new ArrayList<>();
        userResponseList.add(userResponse);

        Mockito.when(createUserService.getAllBasicUsers()).thenReturn(userResponseList);

        ResponseEntity<?> responseEntity = controller.getBasicUsers();
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void getBasicUsers_Failure(){
        List<UserResponse> userResponseList = new ArrayList<>();
        Mockito.when(createUserService.getAllBasicUsers()).thenReturn(userResponseList);
        ResponseEntity<?> responseEntity = controller.getBasicUsers();
        assertEquals(204, responseEntity.getStatusCodeValue());
    }

}