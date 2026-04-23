package com.springjwtexample.springjwtexample.Controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        return "Welcome to JWT Auth Example App" + request.getSession().getId();
    }
    
}
