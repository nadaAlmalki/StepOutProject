package com.example.final_project.Repository;

import com.example.final_project.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Customer_Repository extends JpaRepository<Customer, Integer> {
    Customer findCustomerById(Integer id);


}
