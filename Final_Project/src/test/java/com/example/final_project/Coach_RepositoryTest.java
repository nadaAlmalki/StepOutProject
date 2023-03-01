package com.example.final_project;


import com.example.final_project.Model.Coach;
import com.example.final_project.Repository.Coach_Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class Coach_RepositoryTest {
    @Autowired
    Coach_Repository coachRepository;
    Coach coach;

    public Coach_RepositoryTest() {
    }

    @BeforeEach
    void setUp() {
        this.coach = new Coach( null,  "Mohammed","Male","0283948", "Jeddah", "Alnahdah","EQF-2", 5, (List)null,(List)null,(List)null,null);
    }

    @Test
    public void findCustomerById() {
        this.coachRepository.save(this.coach);
        Coach coach1 = this.coachRepository.findCoachById(this.coach.getId());
        Assertions.assertThat(coach1).isEqualTo(this.coach);
    }


    @Test
    public void findCoachByCity() {
        this.coachRepository.save(this.coach);
       List<Coach> coach1 = this.coachRepository.findCoachByCity(this.coach.getCity());
        Assertions.assertThat(((Coach)coach1.get(0)).getCity().equals(this.coach.getCity()));
    }


    @Test
    public void findCoachByGender() {
        this.coachRepository.save(this.coach);
        List<Coach> coach1 = this.coachRepository.findCoachByGender(this.coach.getGender());
        Assertions.assertThat(((Coach)coach1.get(0)).getCity().equals(this.coach.getGender()));
    }
}

