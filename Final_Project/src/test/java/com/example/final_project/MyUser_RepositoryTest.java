package com.example.final_project;
import com.example.final_project.Model.MyUser;
import com.example.final_project.Repository.MyUser_Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyUser_RepositoryTest {
    @Autowired
    MyUser_Repository myUserRepository;
    MyUser myUser;

    public MyUser_RepositoryTest() {
    }

    @BeforeEach
    void setUp() {

    this.myUser = new MyUser(null, "Mohammed", "1234", "Customer", null, null);
    }

    @Test
    public void findMyUserById() {
        this.myUserRepository.save(this.myUser);
        MyUser user = this.myUserRepository.findMyUserById(this.myUser.getId());
        Assertions.assertThat(user).isEqualTo(this.myUser);
    }

    @Test
    public void findMyUserByUsername() {
        this.myUserRepository.save(this.myUser);
        MyUser user = this.myUserRepository.findMyUserByUsername(this.myUser.getUsername());
        Assertions.assertThat(user).isEqualTo(this.myUser);
    }
}

