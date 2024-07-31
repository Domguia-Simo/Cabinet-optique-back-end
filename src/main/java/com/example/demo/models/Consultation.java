package com.example.demo.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long consultation_id;
    private String date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Consultation(){

    }

    public Consultation(String date ,String status){
        this.date = date;
        this.status = status;
    }

    // setter
    public void setId(long id){this.consultation_id = id;}
    public void setDate(String date){this.date = date;}
    public void setStatus(String status){this.status = status;}
    public void setUser(User u){this.user = u;}
    // getter
    public long getId(){return consultation_id;}
    public String getDate(){return date;}
    public String getStatus(){return status;}
    public User getUser(){return user;}
}
