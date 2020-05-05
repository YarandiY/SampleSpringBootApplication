package com.example.SpringBootApp.service;


//import com.example.SpringBootApp.repository.UserRepository;
import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void printAllUserNames(){
        userRepository.findAll().forEach(u -> {
            System.out.print(u.getName());
        });
    }

    @Override
    public void addUser(UserD userD) {
        if (userD.getName() == null || userD.getName().isEmpty())
            throw new RuntimeException("name is empty");
        User userModel = new User();
        userModel.setName(userD.getName());
        userRepository.save(userModel);
    }

}
