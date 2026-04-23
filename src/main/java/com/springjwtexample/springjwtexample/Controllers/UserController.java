package com.springjwtexample.springjwtexample.Controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springjwtexample.springjwtexample.Entities.User;
import com.springjwtexample.springjwtexample.Services.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
// @RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "WELCOME";
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return service.signup(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String result = service.verify(user);
        if (result.equals("ACTION_FAILED")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }

        return ResponseEntity.ok(Map.of("token", result));
    }

    @GetMapping("/list-all")
    public List<User> list() {
        return service.listAll();
    }
    
}
