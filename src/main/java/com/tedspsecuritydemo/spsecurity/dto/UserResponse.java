package com.tedspsecuritydemo.spsecurity.dto;

import com.tedspsecuritydemo.spsecurity.model.Role;
import com.tedspsecuritydemo.spsecurity.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    public String name;
    public String lastName;
    public List<Role> roles;
    public String email;
    public int id;

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
