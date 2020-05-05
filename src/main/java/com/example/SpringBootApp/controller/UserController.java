package com.example.SpringBootApp.controller;


import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController{

    @Autowired
    private UserService userService;



    @PostMapping("/add")
    public @ResponseBody String addUser(@RequestBody UserD userD) {
        System.out.println(userD.getName());
        userService.addUser(userD);
        return "Hello World!!!";
    }

}


