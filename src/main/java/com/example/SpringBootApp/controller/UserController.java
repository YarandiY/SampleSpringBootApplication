package com.example.SpringBootApp.controller;


import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController{

    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public @ResponseBody String addUser(@RequestBody @Valid UserD userD, BindingResult bindingResult) {
        logger.info("/users/add with post method was called");
        if (bindingResult.hasErrors())
            logger.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        userService.addUser(userD);
        return userD.getName() + " added to DB";
    }

    @GetMapping("/all")
    public @ResponseBody List<User> getUsers(){
        logger.info("/users/all with get method was called");
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUser(@PathVariable int id){
        logger.info("/users/{id} with get method was called");
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteUser(@PathVariable int id){
        logger.info("/users/{id} with delete method was called");
        userService.deleteUser(id);
        return "user removed";
    }

    @PutMapping("/{id}")
    public @ResponseBody User updateUser(@PathVariable int id, @RequestBody UserD userD){
        logger.info("/users/{id} with put method was called");
        return userService.updateUser(id, userD);
    }

    @GetMapping("")
    public @ResponseBody List<User> getUsersByName(@RequestParam("name") String name){
        logger.info("/users with get method was called");
        return userService.getUsersByName(name);
    }

    @GetMapping("/search")
    public @ResponseBody List<User> searchByName(@RequestParam("name") String name){
        logger.info("/users/search with get method was called");
        return userService.searchByName(name);
    }

}


