package com.example.final_project.Controller;

import com.example.final_project.Model.Coach;
import com.example.final_project.Model.Training_Services;
import com.example.final_project.Services.OrderServices;
import com.example.final_project.Services.TraingSrevice_Services;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/training")
public class Trainaing_Controller {
    private final TraingSrevice_Services traingSreviceServices;
    private final OrderServices orderServices;

    @GetMapping("/get")
    public ResponseEntity getAllTrainingServices() {
        List<Training_Services> trainingServices= traingSreviceServices.getAllTrainingServices();
        return ResponseEntity.status(200).body(trainingServices);
    }

    @PostMapping("/add")
    public ResponseEntity addNewTraining(@Valid @RequestBody Training_Services training_services) {
        traingSreviceServices.addNewTraining(training_services);
        return ResponseEntity.status(200).body("Training Service  has been added successfully ");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTraining(@PathVariable Integer id, @Valid @RequestBody Training_Services training_services) {
        traingSreviceServices.updateTraining(id, training_services);

        return ResponseEntity.status(200).body("Training Service  has been updated successfully");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrainingServices(@PathVariable Integer id) {

        traingSreviceServices.deleteTrainingServices(id);

        return ResponseEntity.status(200).body("Training Service  has been deleted successfully");
    }
    @PutMapping("/{coach_id}/Training/{training_id}")
    public ResponseEntity assignCoachToTrainingServices(@PathVariable Integer coach_id, @PathVariable Integer training_id){
        traingSreviceServices.assignCoachToTrainingServices(coach_id, training_id);
        return ResponseEntity.status(200).body("COACH ASSIGN TRAINING SERVICES");
    }

    //This endpoint for display coaches according category
    @GetMapping("/getCoach_ByCatogary/{tr_id}/{customer_id}")
    public ResponseEntity DisplayCoatches_ByCatogary(@PathVariable Integer tr_id,@PathVariable Integer customer_id)
    {List<Coach> coaches=traingSreviceServices.DisplayCoatches_ByCatogary(tr_id,customer_id);
        return ResponseEntity.status(200).body(coaches);
    }

}
