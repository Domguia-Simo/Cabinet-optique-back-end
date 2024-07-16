package com.example.demo.models;

import java.util.Date;


public class Transaction {
    
    // enum Status{
    //     pending ,failed ,success;
    // }

    private long transaction_id;
    private Date date;
    private long amount;
    private String status;
    private String type;

    public Transaction(){

    }

    public Transaction(Date date ,long amount ,String status ,String type){
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    // getters
    public long getId(){return transaction_id;}
    public Date getDate(){return date;}
    public long getAmount(){return amount;}
    public String getStatus(){return status;}
    public String getType(){return type;}

    // setter
    public void setId(long id){this.transaction_id = id;}
    public void setDate(Date date){this.date = date;}
    public void setAmount(long amount){this.amount = amount;}
    public void setStatus(String status){this.status = status;}
    public void setType(String type){this.type = type;}

}
