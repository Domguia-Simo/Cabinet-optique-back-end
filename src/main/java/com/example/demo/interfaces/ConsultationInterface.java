package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.models.Consultation;

public interface ConsultationInterface {
    
    public List<Consultation> getConsultations();
    public void deleteConsultation(Long id);
    public void createConsultaiton(Consultation consultation);
    public void updateConsultation(Consultation consultation);

}
