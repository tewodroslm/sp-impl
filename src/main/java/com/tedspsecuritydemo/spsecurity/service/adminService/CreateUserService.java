package com.tedspsecuritydemo.spsecurity.service.adminService;

import com.tedspsecuritydemo.spsecurity.dto.*;
import com.tedspsecuritydemo.spsecurity.dto.mapper.UserResponseDtoMapper;
import com.tedspsecuritydemo.spsecurity.model.Manager;
import com.tedspsecuritydemo.spsecurity.model.Payment;
import com.tedspsecuritydemo.spsecurity.model.Role;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.repository.PaymentRepository;
import com.tedspsecuritydemo.spsecurity.repository.RolesRepository;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@Slf4j
@Builder
public class CreateUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserResponseDtoMapper userResponseDtoMapper = Mappers.getMapper(UserResponseDtoMapper.class);


    // create user
    public UserDto createUser(SignUpDto signUpDto){
        log.info("Create user service: Entered");
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            log.error("User Already Exists!");
            return null;
        }
//        Role role = Role.builder().role(signUpDto.getRole()).build();

        Role role = rolesRepository.findByRole(signUpDto.getRole());

        Set<Role> hs = new HashSet<>();
        if(role == null){
            role = checkRoleExist(signUpDto.getRole());
        }
        hs.add(role);
        Users u;

        if(signUpDto.getRole().equals("MANAGER")){
            log.info("Creating Manager");
            Manager user = Manager.builder()
                    .email(signUpDto.getEmail())
                    .name(signUpDto.getName())
                    .active(signUpDto.getActive())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .lastName(signUpDto.getLastName())
                    .approveLimit(signUpDto.getApproveLimit())
                    .roles(hs)
                    .build();
            u = userRepository.save(user);
        }else {
            log.info("Creating User");

            Users user = Users.builder()
                    .email(signUpDto.getEmail())
                    .name(signUpDto.getName())
                    .active(signUpDto.getActive())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .lastName(signUpDto.getLastName())
                    .roles(hs)
                    .build();
            log.debug("User val ==> ", user);
            u = userRepository.save(user);

        }

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

    public List<UserResponse> getAllBasicUsers(){
        List<Users> u = userRepository.getMUsers("Users");
        List<UserResponse> userResponses = userResponseDtoMapper.getBasicUser(u);
        return userResponses;
    }

    public List<ManagerResponse> getAllManager(){
        log.info("Service: get all manager");

        List<Users> u = userRepository.getMUsers("Manager");
        log.info(u.toString());
        List<Manager> m = new ArrayList<>();
        for(Users us : u){
            m.add((Manager) us);
        }
        List<ManagerResponse> userResponses = userResponseDtoMapper.getManagers(m);
        return userResponses;
    }

    public Users deleteAgent(String id) throws Exception{
        Users user = userRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new Exception("User not found"));
        userRepository.delete(user);
        return user;
    }

}
