package com.example.SpringBootApp.service;


import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();
    void addUser(UserD userD);
    void deleteUser(int userId);
    User getUser(int userId);
    User updateUser(int userId, UserD userD);
    List<User> getUsersByName(String name);
    List<User> searchByName(String name);


    default boolean checkUserName(String name){
        return name != null && name.length() > 1 && name.length() < 10;
    }

    default boolean checkUserPass(String pass){
        return pass != null && pass.length() > 5 && pass.length() < 10;
    }

    default boolean checkUserAge(int age){
        return age > 6 && age < 100;
    }




}
