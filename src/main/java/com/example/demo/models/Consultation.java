package com.example.demo.models;

import java.util.Date;

public class Consultation {

    private long consultaion_id;
    private Date date;

    public Consultation(){

    }

    public Consultation(Date date){
        this.date = date;
    }

    // setter
    public void setId(long id){this.consultaion_id = id;}
    public void setDate(Date date){this.date = date;}

    // getter
    public long getId(){return consultaion_id;}
    public Date getDate(){return date;}
}
