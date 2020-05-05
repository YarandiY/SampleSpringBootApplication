package com.example.SpringBootApp.controller;


import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserService userService;

    @PostMapping("")
    public @ResponseBody String addUser(@RequestBody UserD userD) {
        System.out.println(userD.getName());
        userService.addUser(userD);
        return userD.getName() + " added to DB";
    }

    @GetMapping("")
    public @ResponseBody List getUsers(){
        return userService.getUsers();
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return "user removed";
    }

}


