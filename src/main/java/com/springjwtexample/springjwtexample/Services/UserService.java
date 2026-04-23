package com.springjwtexample.springjwtexample.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springjwtexample.springjwtexample.Entities.User;
import com.springjwtexample.springjwtexample.Repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);

    public User signup(User user) {
        user.setPasswrd(
                bcrypt.encode(
                        user.getPasswrd()));

        return repo.save(user);
    }

    public List<User> listAll() {
        return repo.findAll();
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPasswrd()));

        return (authentication.isAuthenticated())
                ? jwtService.generateToken(user.getUsername())
                : "ACTION FAILED";
    }
}
