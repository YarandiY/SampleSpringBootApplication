package com.example.SpringBootApp.service;


import com.example.SpringBootApp.controller.UserController;
import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUsers(){
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User addUser(UserD userD) {
        User userModel = new User(userD);
        userRepository.save(userModel);
        logger.info("a user with name " + userModel.getName() + " with id " + userModel.getId() + " created");
        return userModel;
    }

    @Override
    public void deleteUser(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.orElseThrow(() ->new RuntimeException("user doesn't exist"));
        userRepository.deleteById(userId);
        logger.info("the user with id " + userId + "deleted");
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user doesn't exist"));
    }

    @Override
    public User updateUser(int userId, UserD userD) {
        User user = getUser(userId);
        if (checkUserName(userD.getName()))
            user.setName(userD.getName());
        if(checkUserPass(userD.getPassword()))
            user.setPassword(userD.getPassword());
        if(checkUserAge(userD.getAge()))
            user.setAge(userD.getAge());
        userRepository.save(user);
        logger.info("the user with id " + userId + " updated");
        return user;
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findAllByName(name);
    }

    @Override
    public List<User> searchByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }



}
