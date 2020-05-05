package com.example.SpringBootApp.service;


import com.example.SpringBootApp.domain.UserD;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void printAllUserNames();
    void addUser(UserD userD);
}
