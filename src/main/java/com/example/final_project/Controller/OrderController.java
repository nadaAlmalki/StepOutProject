package com.example.final_project.Controller;


import com.example.final_project.Model.Customer;
import com.example.final_project.Model.Orter_table;
import com.example.final_project.Services.OrderServices;
import com.example.final_project.Services.TraingSrevice_Services;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderServices orderServices;

    @GetMapping("/getAll-Order")
    public ResponseEntity getAllOrders() {
        List<Orter_table> orders= orderServices.getAllOrders();
        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/add")
    public ResponseEntity addNewOrder(@Valid @RequestBody Orter_table orderTable) {
        orderServices.addNewOrder(orderTable);
        return ResponseEntity.status(200).body("Order  has been added successfully ");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @Valid @RequestBody Orter_table orderTable) {
        orderServices.updateOrder(id,orderTable);
        return ResponseEntity.status(200).body("Order has been updated successfully");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        orderServices.deleteOrder(id);
        return ResponseEntity.status(200).body("Order  has been deleted successfully");
    }
    @PutMapping("/{training_id}/order-train/{order_id}")
    public ResponseEntity assignTrainingServicesToOrder(@PathVariable Integer training_id,@PathVariable Integer order_id){
        orderServices.assignTrainingServicesToOrder(training_id,order_id);
        return ResponseEntity.status(200).body("TRAINING SERVICES ASSIGN TO ORDER ");
    }

    //1.Get Order Details by Order Id
    @GetMapping("/details/{coach_id}")
    public ResponseEntity OrderDetails(@PathVariable Integer coach_id){
        List<Orter_table> order = orderServices.OrderDetails(coach_id);
        return ResponseEntity.status(200).body(order);
    }

    //A. Change order status from "Pending" to "Accept and by default 'IN PROGRESS'

    @GetMapping("/accept/{id}")
    public ResponseEntity AcceptStatus(@PathVariable Integer id){
        Orter_table orders = orderServices.AcceptStatus(id);
        return ResponseEntity.status(200).body(orders);

    }
    //B.Change order status from "Accept" to "Completed"
    @GetMapping("/complete/{id}")
    public ResponseEntity CompletedStatus(@PathVariable Integer id){
        Orter_table orders = orderServices.CompletedStatus(id);
        return ResponseEntity.status(200).body(orders);}

        //D. Change order status from "Pending" to "REJECT"
    @GetMapping("/reject/{id}")
    public ResponseEntity RejectStatus(@PathVariable Integer id){
        Orter_table orders = orderServices.RejectStatus(id);
        return ResponseEntity.status(200).body(orders);

    }

    @GetMapping("/Get_Discount/{customer_id}")
    public ResponseEntity discount_Price(@PathVariable Integer customer_id)
    {
        Double price_discount= orderServices.discount_Price(customer_id);
        return ResponseEntity.status(200).body("The total price after discount  :"+price_discount);

    }
}
