package com.example.final_project.Repository;

import com.example.final_project.Model.Orter_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_Repositary extends JpaRepository<Orter_table,Integer> {
    //Set<Order_table> findOrder_tableById(Integer Id);

    Orter_table findOrder_tableById(Integer id);

    Orter_table findOrder_tableByStatus(String status);

    List<Orter_table> findOrder_tableByCoachId(Integer id);


    Orter_table findOrter_tableByCustomerId(Integer id);


}