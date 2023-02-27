package com.example.final_project.Services;

import com.example.final_project.ApiException;
import com.example.final_project.DTO.UserCustomerDTO;
import com.example.final_project.Model.*;
import com.example.final_project.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Service
@RequiredArgsConstructor
public class Customer_Service {
    private final Customer_Repository customerRepository;
    private final Order_Repositary orderRepositary;
    private final MyUser_Repository myUserRepository;
    private final Coach_Repository coachRepository;
    private final Training_Repositary trainingRepositary;

    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer id, Customer newCustomer) {
        Customer oldCustomer = customerRepository.findCustomerById(id);
        if (oldCustomer == null) {
            throw new ApiException("Customer not found");
        }
        oldCustomer.setName(newCustomer.getName());
        oldCustomer.setGender(newCustomer.getGender());
        oldCustomer.setPhone_number(newCustomer.getPhone_number());
        oldCustomer.setEmail(newCustomer.getEmail());
        oldCustomer.setAge(newCustomer.getAge());
        oldCustomer.setLength(newCustomer.getLength());
        oldCustomer.setWeight(newCustomer.getWeight());
        oldCustomer.setHealth_problem(newCustomer.getHealth_problem());
        oldCustomer.setCity(newCustomer.getCity());
        oldCustomer.setAddress(newCustomer.getAddress());
        customerRepository.save(oldCustomer);
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        customerRepository.delete(customer);
    }

    //Customer have different orders(1:M)
    public void OrdersAssignedtoCutomer(Integer customer_id, Integer order_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        Orter_table order = orderRepositary.findOrder_tableById(order_id);
        if (customer == null || order == null) {
            throw new ApiException("Customer or Order not found");
        }
        order.setCustomer(customer);
        orderRepositary.save(order);

    }

    //2.Get Customers Details by Customer_id with order details.
    public Customer getListCustomer(Integer id) {
        Orter_table orderTable = orderRepositary.findOrder_tableById(id);
        Customer customer = customerRepository.findCustomerById(id);
        if ( customer != null && customer.getId() == id && orderTable != null) {

            return customer;

        }

        throw new ApiException("Customer not found");
    }


    public List<Coach> Display_CoachByCatogary(Integer customer_id,Integer tr_id)
    {
        Customer customer=customerRepository.findCustomerById(customer_id);

        Training_Services trainingServices=trainingRepositary.findTraining_ServicesById(tr_id);

        if (trainingServices == null || customer == null) {
            throw new ApiException("customer Or training services not Found");
        }
        String catogary = trainingServices.getCategory();
        String city_customer = customer.getCity();
        List<Coach> coaches=new ArrayList<>();
        if (catogary.equals("In_Home") || catogary.equals("Out_Home")){
            coaches = coachRepository.findCoachByCity(city_customer);

            return coaches;}

        else
            return coachRepository.findAll();

    }



    public List<Coach> SearchCoachesBY_Gender(Integer customer_id, String gender)
    {
        Customer customer=customerRepository.findCustomerById(customer_id);
        List<Coach>coaches=coachRepository.findCoachByGender(gender);


        if ( customer == null) {
            throw new ApiException("customer  not Found");
        }

        if (gender.equals("Female"))
            return coachRepository.findCoachByGender("Female");


        else
            return coachRepository.findCoachByGender("Male");

    }
    public List<Coach> Display_CoachByGender(Integer customer_id)
    {
        Customer customer=customerRepository.findCustomerById(customer_id);


        if ( customer == null) {
            throw new ApiException("customer  not Found");
        }
        String gender= customer.getGender();
        if (gender.equals("Female"))
            return coachRepository.findCoachByGender("Female");


        else
            return coachRepository.findCoachByGender("Male");

    }

    public void addUser_Customer(UserCustomerDTO userCustomerDTO){
        MyUser myUser=myUserRepository.findMyUserById(userCustomerDTO.getUser_id());
        if(myUser==null){
            throw new ApiException("my User not found");}
     Customer customer = new Customer(null, userCustomerDTO.getName(), userCustomerDTO.getGender(), userCustomerDTO.getPhone_number(),
             userCustomerDTO.getEmail(), userCustomerDTO.getAge(), userCustomerDTO.getLength(), userCustomerDTO.getWeight(), userCustomerDTO.getHealth_problem(),
             userCustomerDTO.getCity(), userCustomerDTO.getAddress(),  myUser, null);
        customerRepository.save(customer);

    }

    public List<MyUser> AllCustomersbyID(Integer id,UserCustomerDTO userCustomerDTO)
    {
        MyUser  myUser=myUserRepository.findMyUserById(userCustomerDTO.getUser_id());
        if(myUser==null){
            throw new ApiException("my User not found");}
        return myUserRepository.findAll();
    }
}

