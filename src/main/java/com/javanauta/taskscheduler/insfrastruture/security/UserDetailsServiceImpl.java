package com.javanauta.taskscheduler.insfrastruture.security;

import com.javanauta.taskscheduler.business.dto.UserDTO;
import com.javanauta.taskscheduler.insfrastruture.security.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    UserClient client;

    public UserDetails loadUserData(String email, String token){
        UserDTO userDTO = client.findUserByEmail(email, token);

        return User
                .withUsername(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
