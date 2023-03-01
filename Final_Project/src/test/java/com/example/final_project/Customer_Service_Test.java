package com.example.final_project;

import com.example.final_project.Model.Customer;
import com.example.final_project.Model.MyUser;

import com.example.final_project.Repository.Customer_Repository;
import com.example.final_project.Repository.MyUser_Repository;

import com.example.final_project.Services.Customer_Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class Customer_Service_Test {

    @InjectMocks
    Customer_Service customerService;
    @Mock
    Customer_Repository customerRepository;

    @Mock
    MyUser_Repository myUserRepository;

    MyUser user ,user1;
    Customer customer1,customer2,customer3;

    List<Customer>customers;
    @BeforeEach
    void setUp(){
        user=new MyUser(null,"Ahmed","123456","Customer",null,null);
        user1= new MyUser(1,"jana","1598775","Customer",null,null);
        customer1=new Customer(null,"Nasser","Male","456554545","sal@hotmail.com",40,155,70,"Not found","Makkah","ABCDCD",user,null);
        customer2=new Customer(2,"Majed","Male","455556565656","koko@hotmail.com",30,180,150,"not found","Ryiadh","WWWWWWWW",user, null);
        customer3=new Customer(null,"Dalia","Female","45545454545","klklkl@hotmail.com",25,180,55,"Not found","Tubook","erewew",user,null);

        customers=new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

    }




    @Test
    public void getAllCustomerTest(){
        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer>customerList=customerService.getCustomer();
        Assertions.assertEquals(3,customerList.size());
        verify(customerRepository,times(1)).findAll();
    }

    @Test
    public void  updateCustomerTesting()
    {
        when(customerRepository.findCustomerById(customer2.getId())).thenReturn(customer2);
        customerService.updateCustomer(customer2.getId(),customer2);
        verify(customerRepository,times(1)).findCustomerById(customer2.getId());
        verify(customerRepository,times(1)).save(customer2);
    }




}
