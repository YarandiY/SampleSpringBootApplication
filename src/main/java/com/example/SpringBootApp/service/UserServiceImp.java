package com.example.SpringBootApp.service;


//import com.example.SpringBootApp.repository.UserRepository;
import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUsers(){
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public void addUser(UserD userD) {
        if (userD.getName() == null || userD.getName().isEmpty())
            throw new RuntimeException("name is empty");
        User userModel = new User();
        userModel.setName(userD.getName());
        System.out.println(userRepository.save(userModel));
    }

    @Override
    public void deleteUser(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.orElseThrow(() ->new RuntimeException("user doesn't exist"));
        userRepository.deleteById(userId);
    }

}
