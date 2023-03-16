package com.tedspsecuritydemo.spsecurity.service.adminService;

import com.tedspsecuritydemo.spsecurity.dto.SignUpDto;
import com.tedspsecuritydemo.spsecurity.dto.UserDto;
import com.tedspsecuritydemo.spsecurity.dto.UserRequest;
import com.tedspsecuritydemo.spsecurity.dto.UserResponse;
import com.tedspsecuritydemo.spsecurity.model.Role;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.repository.RolesRepository;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Builder
public class CreateUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // create user
    public UserDto createUser(SignUpDto signUpDto){
        log.info("Create user service: Entered");
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return null;
        }
//        Role role = Role.builder().role(signUpDto.getRole()).build();

        Role role = rolesRepository.findByRole(signUpDto.getRole());

        Set<Role> hs = new HashSet<>();
        log.info("ROle --> ", role);
        if(role == null){
            role = checkRoleExist(signUpDto.getRole());
        }
        hs.add(role);
//        user.setRoles(Arrays.asList(role));
//        userRepository.save(user);

        Users user = Users.builder()
                .email(signUpDto.getEmail())
                .name(signUpDto.getName())
                .active(signUpDto.getActive())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .lastName(signUpDto.getLastName())
                .roles(hs)
                .build();
        log.debug("User val ==> ", user);
        Users u = userRepository.save(user);
        if(u != null){
            return UserDto.builder()
                    .uname(u.getName())
                    .role(u.getRoles())
                    .build();
        }

        return null;
    }

    private Role checkRoleExist(String r){
        Role role = new Role();
        role.setRole(r);
        return rolesRepository.save(role);
    }


}
