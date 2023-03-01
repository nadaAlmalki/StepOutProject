package com.example.final_project.Controller;

import com.example.final_project.Model.Coach;
import com.example.final_project.Model.Reviews;
import com.example.final_project.Services.Reviews_Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/review")
@RestController
@RequiredArgsConstructor

public class Reviews_Controller {

    private final Reviews_Service reviewsService;
    @GetMapping("/get")
    public ResponseEntity getReviews(){
        List<Reviews> reviews = reviewsService.getReviews();
        return ResponseEntity.status(200).body(reviews);
    }
    @PostMapping("/add/{id}")
    public ResponseEntity addReviews(@Valid @RequestBody Reviews reviews, @PathVariable Integer id){
        reviewsService.addReviews(reviews, id);
        return ResponseEntity.status(200).body("Reviews added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateReviews(@PathVariable Integer id, @Valid @RequestBody Reviews reviews){
        reviewsService.updateReviews(id, reviews);
        return ResponseEntity.status(200).body("Reviews update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReviews(@PathVariable Integer id){
        reviewsService.deleteReviews(id);
        return ResponseEntity.status(200).body("Reviews deleted");
    }



}
