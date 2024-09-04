package com.example.demo.controllers;

import com.example.demo.models.Consultation;
import com.example.demo.services.ConsultationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/consultation")
class ConsultationController{

    @Autowired
    ConsultationServiceImp consultationServiceImp;

    @GetMapping("/get-consultation")
    public ResponseEntity<?> getConsultations(){
        return ResponseEntity.status(200).body(consultationServiceImp.getConsultations());
    }

    @GetMapping("/get-user-consultations/{id}")
    public ResponseEntity<?> getUserConsultations(@PathVariable("id") long id){
        return ResponseEntity.status(200).body(consultationServiceImp.getUserConsultations(id));
    }

    @PostMapping("/create-consultation/{id}")
    public ResponseEntity<?> createConsultation(@RequestBody Map<String ,?> consultation ,@PathVariable("id") long id){
        String status = (String) consultation.get("status");
        String date = ( String) consultation.get("date");
//        long id = (long) consultation.get("userId");
        return new ResponseEntity(consultationServiceImp.createConsultaiton(date ,status ,id) , HttpStatus.CREATED);
    }


    @DeleteMapping("/delete-consultation/{id}")
    public ResponseEntity<?> deleteConsultation(@PathVariable long id){
        consultationServiceImp.deleteConsultation(id);
        return new ResponseEntity<>("Consultation deleted correctly",HttpStatus.ACCEPTED);
    }


    @PutMapping("/update-consultation")
    public ResponseEntity<?> updateConsultation(@RequestBody Consultation consultation){
        consultationServiceImp.updateConsultation(consultation);
        return new ResponseEntity<>("consultation updated correctly",HttpStatus.OK);
    }


}