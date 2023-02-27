package com.example.final_project.Services;

import com.example.final_project.ApiException;
import com.example.final_project.Model.Coach;
import com.example.final_project.Model.Customer;
import com.example.final_project.Model.Training_Services;
import com.example.final_project.Repository.Coach_Repository;
import com.example.final_project.Repository.Customer_Repository;
import com.example.final_project.Repository.Order_Repositary;
import com.example.final_project.Repository.Training_Repositary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraingSrevice_Services {

    private final Training_Repositary training_repositary;

    private final Coach_Repository coachRepository;
    private final Customer_Repository customerRepository;

    public List<Training_Services> getAllTrainingServices() {
        return training_repositary.findAll();
    }

    public void addNewTraining(Training_Services training_services) {
        training_repositary.save(training_services);

    }

    public void updateTraining(Integer id,Training_Services training_services){
        Training_Services old_Training=training_repositary.getById(id);
        if(old_Training==null){
            throw new ApiException("Training service is not found");
        }
        old_Training.setName(training_services.getName());
        old_Training.setCategory(training_services.getCategory());
        old_Training.setTime_Period(training_services.getTime_Period());
        old_Training.setPrice(training_services.getPrice());
        training_repositary.save(old_Training);
    }

    public void deleteTrainingServices(Integer id) {
        Training_Services old_Training=training_repositary.getById(id);

        if (old_Training == null) {
            throw new ApiException("Training service is not found");
        } else
            training_repositary.deleteById(id);

    }
    public void assignCoachToTrainingServices(Integer coach_id,Integer training_id) {
        Coach coach = coachRepository.findCoachById(coach_id);
        Training_Services training_services = training_repositary.findTraining_ServicesById(training_id);

        if (coach == null || training_services == null) {
            throw new ApiException("coach Or training services not Found");
        } else {
            training_services.setCoach(coach);
            training_repositary.save(training_services);

        }
    }


    //This endpoint for display coaches according category
    public List<Coach> DisplayCoatches_ByCatogary(Integer tr_id, Integer customer_id) {
        Training_Services trainingServices = training_repositary.findTraining_ServicesById(tr_id);
        Customer customer = customerRepository.findCustomerById(customer_id);

        if (trainingServices == null || customer == null) {
            throw new ApiException("customer Or training services not Found");
        }

        String catogary = trainingServices.getCategory();
        String city_customer = customer.getCity();

        if (catogary.equals("In_Home") || catogary.equals("Out_Home")) {

            List<Coach> coaches = coachRepository.findCoachByCity(city_customer);
            return coaches;}

        else
            return coachRepository.findAll();




    }


}
