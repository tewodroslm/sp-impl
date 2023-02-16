package com.tedspsecuritydemo.spsecurity.dto;

import com.tedspsecuritydemo.spsecurity.model.Role;

import java.util.Set;

public class UserRequest {

    public UserRequest(){}

    public String uname;
    public Set<Role> role;
    public String email;
    public String password;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
