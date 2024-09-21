package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Consultation;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation ,Long> {

    @Query(value="select * from consultations where user_id=?1" ,nativeQuery = true)
    public List<Consultation> findByUserId(long userId);


//    Query to get the most active user based on consultations
    @Query(value = "select user_id from consultations group by user_id order by count(*) desc limit 3 " ,nativeQuery = true)
    public List<?> findActiveUserByConsultation();

//    public List<?> findMostRequestedDate();

}
