package com.example.final_project.Repository;

import com.example.final_project.Model.Training_Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Training_Repositary extends JpaRepository<Training_Services,Integer> {
    //Training_Services findTraining_ServicesById(Integer id);
    Training_Services findTraining_ServicesById(Integer id);


}
