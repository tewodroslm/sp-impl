package com.tedspsecuritydemo.spsecurity.dto;

import com.tedspsecuritydemo.spsecurity.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    public String uname;
    public String lname;
    public List<String> role;
    public String email;
    public int uId;

    @Override
    public String toString() {
        return "UserResponse{" +
                "uname='" + uname + '\'' +
                ", lname='" + lname + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", userId='" + uId + '\'' +
                '}';
    }
}
