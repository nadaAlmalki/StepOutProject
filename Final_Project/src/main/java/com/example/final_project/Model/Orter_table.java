package com.example.final_project.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter
public class Orter_table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate start_Date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate end_Date;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime time;

    @NotNull(message="total_price should be not empty")
    @Positive(message="total_price must be double and grater than 0")
    private double total_price;

    @NotEmpty(message="status should be not empty")
    @Pattern(regexp="^(PENDING|ACCEPT|IN PROGRESS|REJECT|COMPLETE)$",
            message="Wrong status ,status should be PENDING or ACCEPT or REJECT or COMPLETE only")
    private String status;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "orderTable")
    private List<Training_Services>training_services;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    @JsonIgnore
    private Coach coach;
}
