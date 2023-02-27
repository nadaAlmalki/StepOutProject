package com.example.final_project.Repository;

import com.example.final_project.Model.Coach;
import com.example.final_project.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Reviews_Repository extends JpaRepository<Reviews, Integer> {
    Reviews findReviewsById(Integer id);

}
