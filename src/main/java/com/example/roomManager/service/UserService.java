package com.example.roomManager.service;

import com.example.roomManager.model.User;
import com.example.roomManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


}
