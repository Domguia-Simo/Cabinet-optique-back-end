package com.example.demo.controllers;

import com.example.demo.models.Admin;
import com.example.demo.services.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminServiceImp adminService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Admin admin) {
        System.out.println(admin.getUsername());
        System.out.println(admin.getPassword());

        adminService.registerAdmin(admin);
        return ResponseEntity.ok("Admin registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String ,String> user) {
        System.out.println(user.get("username"));
        System.out.println(user.get("password"));
        Admin admin = adminService.authenticateAdmin(user.get("username"), user.get("password"));
        if (admin != null) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials.");
        }
    }

    @GetMapping("/getUser/{id}")
    public Optional<Admin> findAdmin(@PathVariable Long id){
        return adminService.getAdmin(id);
    }
    @DeleteMapping("/delete/{id}")
    public void  deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);

    }

}
