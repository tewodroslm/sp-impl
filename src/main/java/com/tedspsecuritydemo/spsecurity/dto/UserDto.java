package com.tedspsecuritydemo.spsecurity.dto;

import com.tedspsecuritydemo.spsecurity.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public class UserDto {

    public String uname;
    public Set<Role> role;

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
