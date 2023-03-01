package com.example.final_project;

import com.example.final_project.Controller.Coach_Controller;
import com.example.final_project.Model.Coach;
import com.example.final_project.Model.MyUser;
import com.example.final_project.Services.Coach_Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = Coach_Controller.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class Coach_Controller_test {

    @MockBean
    Coach_Service coachService;

    @Autowired
    MockMvc mockMvc;

    Coach coach1, coach2, coach3;
    MyUser myUser;

    ApiException apiResponse;

    List<Coach> coaches, coachList;

    @BeforeEach
    void setUp() {
        myUser = new MyUser(1, "Ahmed", "123456", "Coach", null, null);
        coach1 = new Coach(1, "Nasser", "Male", "1234567", "Jeddah", "ABC", "CNC",  5,null,null, null, myUser);
        coach2 = new Coach(2, "Rawan", "Female", "7864545", "Tubook", "ffdfdf", "CNC",  10, null,null,null, myUser);
        coach3 = new Coach(4, "Jassem", "Male", "2043430", "JEddah", "Male_fGfg", "CNC",  7, null,null,null, myUser);



        coaches = Arrays.asList(coach1);
        coachList = Arrays.asList(coach2);}



    @Test
    public void addCoach() throws  Exception {
        mockMvc.perform(post("/api/v1/coach/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(coach2)))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteCoach() throws Exception{
        mockMvc.perform(delete("/api/v1/coach/delete/{id}",coach2.getId()))
                .andExpect(status().isOk());

    }






}
