package com.example.final_project;
import com.example.final_project.Model.Reviews;
import com.example.final_project.Repository.Reviews_Repository;
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
public class Reviews_RepositoryTest{
    @Autowired
    Reviews_Repository reviewsRepository;
    Reviews reviews;

    public Reviews_RepositoryTest() {
    }

    @BeforeEach
    void setUp() {
    this.reviews = new Reviews( null, 10, "Very Good", null);

    }

    @Test
    public void findReviewsById() {
        this.reviewsRepository.save(this.reviews);
       Reviews reviews1 = this.reviewsRepository.findReviewsById(this.reviews.getId());
        Assertions.assertThat(reviews1).isEqualTo(this.reviews);
    }
}
