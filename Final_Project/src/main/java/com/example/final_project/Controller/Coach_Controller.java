package com.example.final_project.Controller;

import com.example.final_project.DTO.MyUserCoachDTO;
import com.example.final_project.Model.Coach;
import com.example.final_project.Model.MyUser;
import com.example.final_project.Services.Coach_Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/coach")
@RestController
@RequiredArgsConstructor
public class Coach_Controller {
    private final Coach_Service coachService;

    @GetMapping("/get")
    public ResponseEntity getCoach(){
        List<Coach> coach = coachService.getCoach();
        return ResponseEntity.status(200).body(coach);
    }
    @PostMapping("/add")
    public ResponseEntity addCoach(@Valid @RequestBody Coach coach, Errors errors){
        coachService.addCoach(coach);
        return ResponseEntity.status(200).body("Coach added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCoach(@PathVariable Integer id, @Valid @RequestBody Coach coach, Errors errors){
        coachService.updateCoach(id, coach);
        return ResponseEntity.status(200).body("Coach update");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoach(@PathVariable Integer id){
        coachService.deleteCoach(id);
        return ResponseEntity.status(200).body("Coach deleted");
    }

    //Coach have different orders(1:M)
    @PutMapping("/{coach_id}/order/{order_id}")
    public ResponseEntity OrdersAssignedtoCoach(@PathVariable Integer coach_id, @PathVariable Integer order_id){
        coachService.OrdersAssignedtoCaoch(coach_id,order_id);
        return ResponseEntity.status(200).body("Coach have many orders");
    }

    //Coach have different Reviews(1:M)
    @PutMapping("/{coach_id}/review/{review_id}")
    public ResponseEntity ReviewsAssignedtoCoach(@PathVariable Integer coach_id, @PathVariable Integer review_id){
        coachService.ReviewsAssignedtoCaoch(coach_id,review_id);
        return ResponseEntity.status(200).body("Coach have many reviews");
    }
    @PostMapping("/addUserCoach")
    public ResponseEntity addUser_Coach(@Valid @RequestBody MyUserCoachDTO myUserCoachDTO) {
        coachService.addUser_Coach(myUserCoachDTO);
        return ResponseEntity.status(200).body("User Coach  added");
    }

    @GetMapping("/getAllUserCoach/{id}")
    public  ResponseEntity AllCoachbyID(@PathVariable Integer id,@Valid @RequestBody  MyUserCoachDTO myUserCoachDTO)
    {
        List<MyUser> myUsers=coachService.AllCoachbyID(id,myUserCoachDTO);
        return ResponseEntity.status(200).body(myUsers);
    }
    @PutMapping("/updateUserCoach/{id}")
    public ResponseEntity updateUserCoach(@PathVariable Integer id, @Valid @RequestBody MyUserCoachDTO myUserCoachDTO){
        coachService.updateUserCoach(id,myUserCoachDTO);
        return ResponseEntity.status(200).body("coach update");
    }

    @DeleteMapping("/deleteUsercoach/{id}")
    public ResponseEntity deleteUserCoach(@PathVariable Integer id){
        coachService.deleteUserCoach(id);
        return ResponseEntity.status(200).body("coach deleted");
    }
}
