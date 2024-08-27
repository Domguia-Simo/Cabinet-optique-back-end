package com.example.demo.services;

import com.example.demo.models.Admin;
import com.example.demo.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImp {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bcrypt;

    @Autowired
    public AdminServiceImp(AdminRepository adminRepository, BCryptPasswordEncoder bcrypt) {
        this.adminRepository = adminRepository;
        this.bcrypt = bcrypt;
    }

    public void registerAdmin(Admin admin) {
        String hashPassword = bcrypt.encode(admin.getPassword());
        admin.setPassword(hashPassword);
        adminRepository.save(admin);
    }

    public Admin authenticateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && bcrypt.matches(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }

    public Optional<Admin> getAdmin(Long id) {
        return adminRepository.findById(id);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

}
