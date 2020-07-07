package com.example.roomManager.security;



import com.example.roomManager.model.Role;
import com.example.roomManager.model.User;
import com.example.roomManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//This is the soul of how the login works.It creates a method that gets the data.It creates a handler for authentication.
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserService userservice;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user=userservice.findByUserName(username);


        if(user != null && user.getEnabled()) {
           /// System.out.println("user--"+user);
            //System.out.println("user--"+user.getRoles());
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());

            return buildUserForAuthentication(user, authorities);
        }

        else {
            throw new UsernameNotFoundException("username not found");
        }

    }

    //it loads the particular roles of the Users.
    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }


    //Creates a user based on what the user inputed in the login form.
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
