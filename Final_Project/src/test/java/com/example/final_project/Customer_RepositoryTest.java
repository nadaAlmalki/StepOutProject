package com.example.final_project;

import com.example.final_project.Model.Customer;
import com.example.final_project.Repository.Customer_Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Customer_RepositoryTest {
    @Autowired
    Customer_Repository customerRepository;
    Customer customer;

    public Customer_RepositoryTest() {
    }

    @BeforeEach
    void setUp() {
        this.customer = new Customer( null, "Mohammed","Male","0283948","Moammed@hotmail.com", 30, 170, 60, "NA", "Jeddah", "Alnahdah",null,(List)null);
    }

    @Test
    public void findCustomerById() {
        this.customerRepository.save(this.customer);
        Customer customer1 = this.customerRepository.findCustomerById(this.customer.getId());
        Assertions.assertThat(customer1).isEqualTo(this.customer);
    }
}
