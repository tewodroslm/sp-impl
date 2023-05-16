package com.tedspsecuritydemo.spsecurity.dto;

import com.tedspsecuritydemo.spsecurity.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerResponse {
    public String name;
    public String lastName;
    public List<Role> roles;
    public String email;
    public int id;
    public int approveLimit;
    @Override
    public String toString() {
        return "UserResponse{" +
                "uname='" + name + '\'' +
                ", lname='" + lastName + '\'' +
                ", role=" + roles +
                ", email='" + email + '\'' +
                ", userId='" + id + '\'' +
                '}';
    }
}

