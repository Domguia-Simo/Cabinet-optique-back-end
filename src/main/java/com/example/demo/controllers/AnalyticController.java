package com.example.demo.controllers;

import com.example.demo.services.AnalyticServiceImp;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/analytics")
@CrossOrigin(origins = "*")
public class AnalyticController {

    @Autowired
    private AnalyticServiceImp analyticServiceImp;


//    Function to get the most order product
    @GetMapping("/order-product")
    public ResponseEntity<?> getMostOrderProduct(){
        return ResponseEntity.status(200).body(analyticServiceImp.getMostOrderProduct());
    }

//    Function to get the most active user
    @GetMapping("/active-user")
    public ResponseEntity<?> getMostActiveUser(){
        return ResponseEntity.status(200).body(analyticServiceImp.getMostActiveUser());
    }

//    Function to get most requested dates
    @GetMapping("/requested-dates")
    public ResponseEntity<?> getMostRequestedDate(){
        return new ResponseEntity<>(analyticServiceImp.getMostRequestedDate(), HttpStatus.OK );
    }


}
