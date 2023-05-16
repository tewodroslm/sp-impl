package com.tedspsecuritydemo.spsecurity.dto.mapper.mapperImp;

import com.tedspsecuritydemo.spsecurity.dto.ManagerResponse;
import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.dto.mapper.UserResponseDtoMapper;
import com.tedspsecuritydemo.spsecurity.model.Manager;
import com.tedspsecuritydemo.spsecurity.model.Payment;
import com.tedspsecuritydemo.spsecurity.model.Role;
import com.tedspsecuritydemo.spsecurity.model.Users;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDtoMapperImp implements UserResponseDtoMapper {

    @Override
    public UserResponse userResponse(Users users) {
        if(users == null) return null;
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(users.getEmail());
        List<Role> userRole = new ArrayList<>(users.getRoles());
        userResponse.setRoles(userRole);
        userResponse.setName(users.getName());
        userResponse.setLastName(users.getLastName());
        userResponse.setId(users.getId());

        return userResponse;
    }

    @Override
    public List<UserResponse> getBasicUser(List<Users> usersList) {
        System.out.println("******* Inside get All Basic Users ********");
        if(usersList == null) return null;

        List<UserResponse> userResponses = new ArrayList<>();
        for(Users users: usersList){
            UserResponse userResponse = new UserResponse();
            userResponse.setEmail(users.getEmail());

            userResponse.setName(users.getName());
            userResponse.setLastName(users.getLastName());
            userResponse.setId(users.getId());

            List<Role> userRole = new ArrayList<>(users.getRoles());
            userResponse.setRoles(userRole);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @Override
    public List<ManagerResponse> getManagers(List<Manager> managers) {
        if(managers == null) return null;

        List<ManagerResponse> mangerResponses = new ArrayList<>();
        for(Manager users: managers){
            ManagerResponse mangerResponse = new ManagerResponse();
            mangerResponse.setEmail(users.getEmail());

            mangerResponse.setName(users.getName());
            mangerResponse.setLastName(users.getLastName());
            mangerResponse.setId(users.getId());

            List<Role> userRole = new ArrayList<>(users.getRoles());
            mangerResponse.setRoles(userRole);
            mangerResponse.setApproveLimit(users.getApproveLimit());
            mangerResponses.add(mangerResponse);
        }
        return mangerResponses;
    }
}
