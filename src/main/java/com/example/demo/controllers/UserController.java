package com.example.demo.controllers;

import java.util.Map;

import com.example.demo.config.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Client;
import com.example.demo.models.User;
import com.example.demo.services.UserServiceImp;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    private final UserServiceImp userServices;
    public UserController(UserServiceImp service){
        this.userServices = service;
    }

    // To get the list of users/clients
    @GetMapping("/get-users")
    public ResponseEntity<Object> getUser(@RequestAttribute String token ){
        System.out.println(token);
        return ResponseEntity.status(200).body(userServices.getClients());
    }

    // Client/user registration
    @PostMapping("/register-user")
    public ResponseEntity<Object> registerUser(@RequestBody User user){
        if(!user.getEmail().isEmpty() || !user.getName().isEmpty() || !user.getPassword().isEmpty() || !user.getPhoneNumber().isEmpty() ){
            System.out.println(user.getEmail());
            System.out.println(user.getName());
            System.out.println(user.getPhoneNumber());
            System.out.println(user.getPassword());

            userServices.register(user);
            return ResponseEntity.status(200).body("User saved correctly");
        }else{
            return ResponseEntity.status(400).body("Please fill the entire form");
        }
    }

    // Function to authenticate a user
    @PostMapping("/login-user")
    public ResponseEntity<Object> loginUser(@RequestBody Map<String ,String> data){
        String email = data.get("email");
        String password = data.get("password");
        System.out.println(data.get("password"));
        System.out.println(data.get("email"));

        Map<String ,?> response = userServices.login(email ,password);
        if(response.get("success") != null){

            return ResponseEntity.status(200).body(response.get("success"));
        }else{
            return ResponseEntity.status(401).body(response.get("error"));
        }
    }

//    Function to update the various user information
    @PutMapping("/update-profile")
    public ResponseEntity<?> updateUserInfo(@RequestBody Map<?,?> user){
        String name = (String) user.get("name");
        String phoneNumber = (String ) user.get("phoneNumber");
        int id = (int) user.get("id");

        System.out.println(name + " " + phoneNumber+" "+id);

        return ResponseEntity.status(200).body(userServices.updateClient(name ,phoneNumber ,id));
    }

//    Function to upload the user's picture
    @PostMapping("/upload-user-picture")
    public ResponseEntity<?> uploadUserPicture(@RequestParam("profile") MultipartFile file){
        return new ResponseEntity<>("" , HttpStatus.OK);
    }


    @GetMapping("/get-summary")
    public ResponseEntity<?> getSummary(){
        return ResponseEntity.status(200).body(userServices.getSummary());
    }



}
