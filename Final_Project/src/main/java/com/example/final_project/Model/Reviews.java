package com.example.final_project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private int rating;
    @Column(columnDefinition = "varchar(30) not null")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    @JsonIgnore
    private Coach coach;
}
