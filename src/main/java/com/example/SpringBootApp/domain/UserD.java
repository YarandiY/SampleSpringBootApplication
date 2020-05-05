package com.example.SpringBootApp.domain;


import com.example.SpringBootApp.model.User;
import lombok.Data;
import lombok.Getter;

@Data
public class UserD {
    @Getter
    private String name;

    public UserD(){
    }

    public UserD(String name){
        this.name = name;
    }
}
