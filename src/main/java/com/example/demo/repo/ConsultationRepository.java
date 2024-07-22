package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Consultation;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation ,Long> {
    
}
