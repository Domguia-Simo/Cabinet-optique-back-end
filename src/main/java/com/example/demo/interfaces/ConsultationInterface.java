package com.example.demo.interfaces;

import java.util.List;
import java.util.Map;

import com.example.demo.models.Consultation;

public interface ConsultationInterface {
    
    public List<Consultation> getConsultations();
    public void deleteConsultation(Long id);
    public Map<String ,?> createConsultaiton(String date ,String status ,Long userId);
    public void updateConsultation(Consultation consultation);

}
