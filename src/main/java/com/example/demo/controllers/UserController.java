package com.example.demo.controllers;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Client;
import com.example.demo.models.User;
import com.example.demo.services.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {

    // @Autowired
    private final UserServiceImp userServices;
    public UserController(UserServiceImp service){
        this.userServices = service;
    }

    // To get the list of users/clients
    @GetMapping("/get-users")
    public ResponseEntity<Object> getUser(){
        return ResponseEntity.status(200).body(userServices.getClients());
    }

    // Client/user registration
    @PostMapping("/register-user")
    public ResponseEntity<Object> registerUser(@RequestBody User user){

        userServices.register(user);

        return ResponseEntity.status(200).body("User saved correctly");
    }

    // Function to authenticate a user
    @PostMapping("/login-user")
    public ResponseEntity<Object> loginUser(@RequestBody Map<String ,String> data){
        String email = data.get("email");
        String password = data.get("password");
        System.out.println(data.get("password"));
        System.out.println(data.get("email"));

        String response = userServices.login(email ,password);

        return ResponseEntity.status(200).body(response);
    }



}
