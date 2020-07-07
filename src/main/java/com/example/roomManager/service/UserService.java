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
        return userRepository.findByUsername(userName);
    }


    public User saveUser(User user_)
    {
        User user=new User();
        user.setEnabled(user_.getEnabled());
        user.setPassword(user_.getPassword());
        user.setUsername(user_.getUsername());
        user.setRoles(user_.getRoles());


        return userRepository.save(user);

    }


}
