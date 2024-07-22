package com.example.demo.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long consultation_id;
    private Date date;
    private String status;

    public Consultation(){

    }

    public Consultation(Date date ,String status){
        this.date = date;
        this.status = status;
    }

    // setter
    public void setId(long id){this.consultation_id = id;}
    public void setDate(Date date){this.date = date;}
    public void setStatus(String status){this.status = status;}

    // getter
    public long getId(){return consultation_id;}
    public Date getDate(){return date;}
    public String getStatus(){return status;}
}
