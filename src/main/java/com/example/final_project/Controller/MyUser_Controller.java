package com.example.final_project.Controller;

import com.example.final_project.Model.MyUser;
import com.example.final_project.Services.MyUser_Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class MyUser_Controller {
    private final MyUser_Service myUserService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        List<MyUser> users= myUserService.getUser();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody MyUser myUser, Errors errors){
            myUserService.addUser(myUser);
            return ResponseEntity.status(200).body("User added.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody MyUser myUser, Errors errors){
        myUserService.updateUser(id, myUser);
        return ResponseEntity.status(200).body("User Updated!");
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity delete(@PathVariable Integer id){
        myUserService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted!");
    }


    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody MyUser myUser){
        myUserService.register(myUser);
        return  ResponseEntity.status(201).body("Admin registered !");
    }


    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("Welcome back!");
    }


}
