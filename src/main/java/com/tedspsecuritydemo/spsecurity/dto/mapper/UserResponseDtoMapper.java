package com.tedspsecuritydemo.spsecurity.dto.mapper;

import com.tedspsecuritydemo.spsecurity.dto.ManagerResponse;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Manager;
import com.tedspsecuritydemo.spsecurity.model.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseDtoMapper {
    UserResponse userResponse(Users users);


    List<UserResponse> getBasicUser(List<Users> users);
    List<ManagerResponse> getManagers(List<Manager> managers);
}
