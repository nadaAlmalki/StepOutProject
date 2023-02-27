package com.example.final_project.Services;

import com.example.final_project.Model.MyUser;
import com.example.final_project.Repository.MyUser_Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MyUser_Repository myUser_repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user=myUser_repository.findMyUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return user;
    }
}

