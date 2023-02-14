package com.tedspsecuritydemo.spsecurity.service;

import com.tedspsecuritydemo.spsecurity.model.CustomUserDetails;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepository.findByName(username);
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        return optionalUsers.map(CustomUserDetails::new).get();
    }
}
