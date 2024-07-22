package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.ConsultationInterface;
import com.example.demo.models.Consultation;
import com.example.demo.repo.ConsultationRepository;

@Service
public class ConsultationServiceImp implements ConsultationInterface {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public List<Consultation> getConsultations(){
        return consultationRepository.findAll(); 
    }
    
    @Override
    public void deleteConsultation(Long id){
        System.out.print("the id of the targett : "+id);
        consultationRepository.deleteById(id);
    }
    
    @Override
    public void createConsultaiton(Consultation consultation){
        consultationRepository.save(consultation);
    }
    
    @Override
    public void updateConsultation(Consultation consultation){
        System.out.print("The id of the target to delete : "+consultation.getId());
        consultationRepository.save(consultation);
    }


}
