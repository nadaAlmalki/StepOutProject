package com.example.final_project.Services;

import com.example.final_project.ApiException;
import com.example.final_project.Controller.Trainaing_Controller;
import com.example.final_project.DTO.MyUserCoachDTO;
import com.example.final_project.Model.Coach;
import com.example.final_project.Model.MyUser;
import com.example.final_project.Model.Orter_table;
import com.example.final_project.Model.Reviews;
import com.example.final_project.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Coach_Service {
    private final Coach_Repository coachRepository;
    private final Order_Repositary orderRepositary;
    private final Reviews_Repository reviewsRepository;

    private final MyUser_Repository myUserRepository;

    private final Training_Repositary trainingRepositary;

    public List<Coach> getCoach(){
        return coachRepository.findAll();
    }
    public void addCoach(Coach coach){
        coachRepository.save(coach);
    }

    public void updateCoach(Integer id, Coach newCoach){
        Coach oldCoach = coachRepository.findCoachById(id);
        if(oldCoach == null){
            throw new ApiException("Coach not found");
        }
        oldCoach.setName(newCoach.getName());
        oldCoach.setGender(newCoach.getGender());
        oldCoach.setPhone_number(newCoach.getPhone_number());
        oldCoach.setCity(newCoach.getCity());
        oldCoach.setLicense(newCoach.getLicense());
        oldCoach.setYear_of_experience(newCoach.getYear_of_experience());
        coachRepository.save(oldCoach);
    }

    public void deleteCoach(Integer id){
        Coach Coach = coachRepository.findCoachById(id);
        if (Coach == null){
            throw new ApiException("Coach not found");
        }
        coachRepository.delete(Coach);
    }


    //Coach have different orders(1:M)
    public void OrdersAssignedtoCaoch(Integer coach_id, Integer order_id){
        Coach coach = coachRepository.findCoachById(coach_id);
        //Order_table order = (Order_table) orderRepositary.findOrder_tableById(order_id);
         Orter_table order = orderRepositary.findOrder_tableById(order_id);
        if(coach == null || order == null){
            throw new ApiException("Coach or Order not found");
        }
        order.setCoach(coach);
        orderRepositary.save(order);

    }


    //Coach have different Reviews(1:M)
    public void ReviewsAssignedtoCaoch(Integer coach_id, Integer review_id){
        Coach coach = coachRepository.findCoachById(coach_id);
        Reviews review = reviewsRepository.findReviewsById(review_id);
        if(coach == null || review == null){
            throw new ApiException("Coach or Review not found");
        }

        review.setCoach(coach);
        reviewsRepository.save(review);
    }

    public void addUser_Coach(MyUserCoachDTO myUserCoachDTO){
        MyUser myUser=myUserRepository.findMyUserById(myUserCoachDTO.getCoach_id());
        Coach coach = new Coach(null, myUserCoachDTO.getName(), myUserCoachDTO.getGender(), myUserCoachDTO.getPhone_number(), myUserCoachDTO.getCity(),
                myUserCoachDTO.getAddress(), myUserCoachDTO.getLicense(), myUserCoachDTO.getYear_of_experience(), null,
                null, null,myUser);
        coachRepository.save(coach);
    }

    public List<MyUser> AllCoachbyID(Integer id,MyUserCoachDTO myUserCoachDTO)
    {
        MyUser  myUser=myUserRepository.findMyUserById(myUserCoachDTO.getCoach_id());
        if(myUser==null){
            throw new ApiException("my User not found");}
        return myUserRepository.findAll();
    }

    public void updateUserCoach(Integer id,MyUserCoachDTO myUserCoachDTO) {
        Coach coach = coachRepository.findCoachById(myUserCoachDTO.getCoach_id());
        if (coach == null)
            throw new ApiException("COACH NOT FOUND");
        coach.setName(myUserCoachDTO.getName());
        coach.setGender(myUserCoachDTO.getGender());
        coach.setPhone_number(myUserCoachDTO.getPhone_number());
        coach.setCity(myUserCoachDTO.getCity());
        coach.setAddress(myUserCoachDTO.getAddress());
        coach.setLicense(myUserCoachDTO.getLicense());
        coach.setYear_of_experience(myUserCoachDTO.getYear_of_experience());
        coachRepository.save(coach);


    }


    public void deleteUserCoach(Integer id)
    {
        Coach coach=coachRepository.findCoachById(id);
        if(coach==null){
            throw new ApiException("coach not found");
        }
        coachRepository.delete(coach);
    }


}
