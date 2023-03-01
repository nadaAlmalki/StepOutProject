package com.example.final_project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @Column(columnDefinition = "varchar(30) not null check (gender= 'Female' or gender='Male')")
    private String gender;
    @Column(columnDefinition = "varchar(10) not null")
    private String phone_number;

    @Column(columnDefinition = "varchar(30) not null")
    private String email;
    @Column(columnDefinition = "int not null")
    private Integer age;
    @Column(columnDefinition = "int not null")
    private Integer length;
    @Column(columnDefinition = "int not null")
    private Integer weight;
    @Column(columnDefinition = "varchar(30) not null")
    private String health_problem;
    @Column(columnDefinition = "varchar(30) not null")
    private String city;
    @Column(columnDefinition = "varchar(30) not null")
    private String address;


    @OneToOne
    @PrimaryKeyJoinColumn
    private MyUser myUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orter_table> orders;




}
