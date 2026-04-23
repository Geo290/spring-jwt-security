package com.springjwtexample.springjwtexample.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springjwtexample.springjwtexample.Repositories.UserRepo;
import com.springjwtexample.springjwtexample.Entities.User;
import com.springjwtexample.springjwtexample.Entities.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " Not Found");
        }

        return new UserPrincipal(user);
    }
}
