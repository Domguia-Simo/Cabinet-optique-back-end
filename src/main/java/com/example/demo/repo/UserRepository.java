package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Client;
import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User ,Long> {

    @Query(value = "select * from users where email=?1" ,nativeQuery=true)
    public Optional<User> findByEmail(String email); 
    
}
