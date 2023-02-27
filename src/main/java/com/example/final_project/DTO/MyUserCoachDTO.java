package com.example.final_project.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserCoachDTO {
    private Integer coach_id;
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @Column(columnDefinition = "varchar(30) not null")
    private String phone_number;
    @Column(columnDefinition = "varchar(30) not null")
    private String license;
    @Column(columnDefinition = "int not null")
    private Integer year_of_experience;
   /* @Column(columnDefinition = "int not null")
    private Integer price;*/

    @NotEmpty(message = "gender should be not empty")
    @Pattern(regexp = "^(Female|Male)$",
            message = "Wrong gender ,gender should be Female or Male only")
    private String gender;

    @Column(columnDefinition = "varchar(30) not null")
    private String city;

    @Column(columnDefinition = "varchar(100) not null")
    private String address;
}