package com.example.final_project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @Column(columnDefinition = "varchar(30) not null check (gender= 'Female' or gender='Male')")
    private String gender;
    @Column(columnDefinition = "varchar(30) not null")
    private String phone_number;

    @Column(columnDefinition = "varchar(30) not null")
    private String city;
    @Column(columnDefinition = "varchar(30) not null")
    private String address;
    @Column(columnDefinition = "varchar(30) not null")
    private String license;
    @Column(columnDefinition = "int not null")
    private Integer year_of_experience;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coach")
    private List<Orter_table> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coach")
    private List<Reviews> reviews;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "coach")
    private List<Training_Services> training_services;


    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private MyUser myUser;
}
