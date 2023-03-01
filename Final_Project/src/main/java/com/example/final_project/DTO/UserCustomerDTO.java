package com.example.final_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCustomerDTO {


    private Integer user_id;

    private String name;

    private String gender;

    private String phone_number;

    private String email;

    private Integer age;

    private Integer length;

    private Integer weight;

    private String health_problem;

    private String city;

    private String address;

}
