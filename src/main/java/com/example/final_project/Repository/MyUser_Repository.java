package com.example.final_project.Repository;

import com.example.final_project.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUser_Repository extends JpaRepository<MyUser, Integer > {

    MyUser findMyUserById(Integer id);
    MyUser findMyUserByUsername(String username);

}
