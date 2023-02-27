package com.example.final_project.Services;

import com.example.final_project.ApiException;
import com.example.final_project.Model.Coach;
import com.example.final_project.Model.Orter_table;
import com.example.final_project.Model.Reviews;
import com.example.final_project.Repository.Order_Repositary;
import com.example.final_project.Repository.Reviews_Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Reviews_Service {
    private final Reviews_Repository reviewsRepository;

    private final Order_Repositary orderRepositary;

     public List<Reviews> getReviews(){
         return reviewsRepository.findAll();
     }
    public void addReviews(Reviews reviews, Integer id){
        Orter_table orterTable = orderRepositary.findOrder_tableById(id);
        if (orterTable != null && orterTable.getStatus().equals("COMPLETE")){
            reviewsRepository.save(reviews);
        }else
            throw new ApiException("Can't add reviews, Your Order is not 'COMPLETE'. ");

    }

    public void updateReviews(Integer id, Reviews newReviews){
        Reviews oldReviews = reviewsRepository.findReviewsById(id);
        if(oldReviews == null){
            throw new ApiException("Reviews not found");
        }
        oldReviews.setRating(newReviews.getRating());
        oldReviews.setComment(newReviews.getComment());
        reviewsRepository.save(oldReviews);
    }

    public void deleteReviews(Integer id){
        Reviews reviews = reviewsRepository.findReviewsById(id);
        if (reviews == null){
            throw new ApiException("Reviews not found");
        }
        reviewsRepository.delete(reviews);
    }



}
