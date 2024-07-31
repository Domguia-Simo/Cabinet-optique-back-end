package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.models.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.ConsultationInterface;
import com.example.demo.models.Consultation;
import com.example.demo.repo.ConsultationRepository;

@Service
public class ConsultationServiceImp implements ConsultationInterface {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private UserRepository userRepository;
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
    public Map<String ,?> createConsultaiton(String date , String status , Long userId){
        Optional<User> Ou = userRepository.findById(userId);
        Consultation cons = new Consultation();

        if(Ou.isPresent()){
            User u = Ou.get();
            cons.setUser(u);
            cons.setStatus(status);
            cons.setDate(date);
            return Map.of("success" ,consultationRepository.save(cons));
        }else{
            return Map.of("error" ,"Invalid user id");
        }

    }
    
    @Override
    public void updateConsultation(Consultation consultation){
        System.out.print("The id of the target to delete : "+consultation.getId());
        consultationRepository.save(consultation);
    }


}
