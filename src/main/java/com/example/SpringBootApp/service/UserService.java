package com.example.SpringBootApp.service;


import com.example.SpringBootApp.domain.UserD;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List getUsers();
    void addUser(UserD userD);
    void deleteUser(int userId);
}
