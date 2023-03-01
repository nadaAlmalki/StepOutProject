package com.example.final_project;

import com.example.final_project.Model.Customer;
import com.example.final_project.Model.MyUser;
import com.example.final_project.Repository.Customer_Repository;
import com.example.final_project.Repository.MyUser_Repository;

import com.example.final_project.Services.MyUser_Service;
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

public class MyUser_Service_Test {
    @InjectMocks
    MyUser_Service myUserService;
    @Mock
    MyUser_Repository myUserRepository;
    @Mock
    Customer_Repository customerRepository;

    MyUser user;

    Customer customer1, customer2, customer3;
    MyUser myUser1, myUser2, myUser3;
    List<MyUser> myUsers;

    List<Customer> todos;

    @BeforeEach
    void setUp() {

        user = new MyUser(null, "Ahmed", "123456", "Customer", null, null);
        myUser2 = new MyUser(1, "jana", "1598775", "Customer", null, null);
        myUser3 = new MyUser(1, "Rahaf", "951753", "Customer", null, null);
        customer1=new Customer(null,"Nasser","Male","456554545","sal@hotmail.com",40,155,70,"Not found","Makkah","ABCDCD",user,null);
        customer2=new Customer(2,"Majed","Male","455556565656","koko@hotmail.com",30,180,150,"not found","Ryiadh","WWWWWWWW",user, null);
        customer3=new Customer(null,"Dalia","Female","45545454545","klklkl@hotmail.com",25,180,55,"Not found","Tubook","erewew",user, null);



        myUsers = new ArrayList<>();
        myUsers.add(myUser1);
        myUsers.add(myUser2);
        myUsers.add(myUser3);
    }





    @Test
    public void getAllUsers()
    {
        when(myUserRepository.findAll()).thenReturn(myUsers);
        List<MyUser>myUserList=myUserService.getUser();
        Assertions.assertEquals(3,myUserList.size());
        verify(myUserRepository,times(1)).findAll();
    }

    @Test
    public void  updateUserTesting()
    {
        when(myUserRepository.findMyUserById(myUser2.getId())).thenReturn(myUser2);
        myUserService.updateUser(myUser2.getId(),myUser2);
        verify(myUserRepository,times(1)).findMyUserById(myUser2.getId());
        verify(myUserRepository,times(1)).save(myUser2);}

    @Test
    public void deleteUserTesting()
    {
        when(myUserRepository.findMyUserById(myUser2.getId())).thenReturn(myUser2);
        myUserService.deleteUser(myUser2.getId());
        verify(myUserRepository,times(1)).findMyUserById(myUser2.getId());
        verify(myUserRepository,times(1)).delete(myUser2);

    }
}
