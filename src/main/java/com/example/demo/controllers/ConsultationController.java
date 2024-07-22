package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Consultation;
import com.example.demo.services.ConsultationServiceImp;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {
    
    @Autowired
    private ConsultationServiceImp consultationService;

    @GetMapping("/get-consultation")
    public ResponseEntity<Object> getConsultation(){
        return ResponseEntity.status(200).body(consultationService.getConsultations()) ;
    }

    @PostMapping("/create-consultation")
    public ResponseEntity<Object> createConsultation(@RequestBody Consultation consultation){
        consultationService.createConsultaiton(consultation);
        return ResponseEntity.status(200).body("consultation registered correctly");
    }

    @DeleteMapping("/delete-consultation/{id}")
    public ResponseEntity<Object> deleteConsultation(@PathVariable("id") Long id){
        consultationService.deleteConsultation(id);
        return ResponseEntity.status(200).body("Consultation deleted corrctly") ;
    }

    @PutMapping("/update-consultation")
    public ResponseEntity<Object> updateConsultation(@RequestBody Consultation consultation){
        System.out.println(consultation.getStatus());
        System.out.println(consultation.getDate());
        System.out.println(consultation.getId());


        consultationService.updateConsultation(consultation);
        return ResponseEntity.status(200).body("consultation updated correctly") ;
    }

}
