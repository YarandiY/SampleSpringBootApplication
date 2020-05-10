package com.example.SpringBootApp.controller;


import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody ResponseEntity<User> addUser(@RequestBody @Valid UserD userD, BindingResult bindingResult) {
        logger.info("/users/add with post method was called");
        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(null, HttpStatus.valueOf(500));
        }
        User user = userService.addUser(userD);
        return new ResponseEntity<>(user, HttpStatus.valueOf(200));
    }

    @GetMapping("/all")
    public @ResponseBody List<User> getUsers(){
        logger.info("/users/all with get method was called");
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<User> getUser(@PathVariable int id){
        logger.info("/users/{id} with get method was called");
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable int id){
        logger.info("/users/{id} with delete method was called");
        userService.deleteUser(id);
        return new ResponseEntity<>("user removed",HttpStatus.OK);
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


