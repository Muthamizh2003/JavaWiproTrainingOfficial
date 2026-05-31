package com.wipro.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecom.models.User;
import com.wipro.ecom.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "APIs for managing users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Operation(summary = "Create a new user")
    public User createUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<User> getUsers() {
        return service.getAllUsers();
    }
}