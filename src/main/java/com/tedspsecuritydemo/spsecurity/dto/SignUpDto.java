package com.tedspsecuritydemo.spsecurity.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private String role;
    private String lastName;
    private int active;
    private int approveLimit;
}
