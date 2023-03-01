package com.example.final_project;
import com.example.final_project.Model.Coach;
import com.example.final_project.Model.Customer;
import com.example.final_project.Model.Orter_table;
import com.example.final_project.Repository.Coach_Repository;
import com.example.final_project.Repository.Order_Repositary;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Order_RepositaryTest {
    @Autowired
    Order_Repositary orderRepositary;
    Orter_table orterTable;
    Coach coach;
    Customer customer;
    public Order_RepositaryTest() {
    }

    @BeforeEach
    void setUp() {
        DateTimeFormatter df = DateTimeFormatter .ofPattern("MM/dd/yyyy");

  this.orterTable = new Orter_table(null,LocalDate.parse("02/02/2023" , df), LocalDate.parse("02/08/2023" , df), LocalTime.parse("10:11:00"), 200, "ACCEPT",(List)null,null, null);

    }
    @Test
    public void findOrder_tableById() {
        this.orderRepositary.save(this.orterTable);
       Orter_table order= this.orderRepositary.findOrder_tableById(this.orterTable.getId());
        Assertions.assertThat(order).isEqualTo(this.orterTable);
    }


    @Test
    public void findOrder_tableByCoachId() {
        this.orderRepositary.save(this.orterTable);
        List<Orter_table> orders = this.orderRepositary.findOrder_tableByCoachId(this.coach.getId());
        Assertions.assertThat(((Orter_table)orders.get(0)).getId().equals(this.coach.getId()));
    }


    @Test
    public void findOrter_tableByCustomerId() {
        this.orderRepositary.save(this.orterTable);
        Orter_table order= this.orderRepositary.findOrter_tableByCustomerId(this.customer.getId());
        Assertions.assertThat(order).isEqualTo(this.orterTable);
    }
}


