package com.example.final_project;
import com.example.final_project.Controller.Customer_Controller;
import com.example.final_project.Controller.OrderController;
import com.example.final_project.Model.Orter_table;
import com.example.final_project.Services.OrderServices;
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
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;



import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = OrderController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class OrderControllerTest {

    @MockBean
    OrderServices orderServices;

    @Autowired
    MockMvc mockMvc;

    Orter_table order1, order2, order3;

    ApiException apiResponse;

    List<Orter_table> order, orterList;

    @BeforeEach
    public void setUp() {
        DateTimeFormatter df = DateTimeFormatter .ofPattern("MM/dd/yyyy");

        order1 =new Orter_table(null, LocalDate.parse("02/02/2023" , df), LocalDate.parse("02/08/2023" , df), LocalTime.parse("10:11:00"), 200, "ACCEPT",(List)null,null, null);
        order2 =new Orter_table(2, LocalDate.parse("02/02/2023" , df), LocalDate.parse("02/08/2023" , df), LocalTime.parse("10:11:00"), 200, "ACCEPT",(List)null,null, null);
        order = Arrays.asList(order1);
        orterList = Arrays.asList(order2);
    }
    @Test
    public void getOrders() throws Exception{
        Mockito.when(orderServices.getAllOrders()).thenReturn(order);

        mockMvc.perform(get("/api/v1/order/getAll-Order"))
                .andExpect(status().isOk());
    }

    @Test
    public void DeleteOrder() throws Exception{
        mockMvc.perform(delete("/api/v1/order/delete/{id}",order2.getId()))
                .andExpect(status().isOk());

    }


}
