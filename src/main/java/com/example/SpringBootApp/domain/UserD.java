package com.example.SpringBootApp.domain;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserD {

    @NotNull(message = "name can't be null")
    @Size(min = 2, max = 10, message = "name size must be between 2 and 10")
    private String name;
    @NotNull(message = "password can't be null")
    @Size(min = 6, max = 10, message = "password size must be between 6 and 10")
    private String password;
    @Range(min = 5, max = 99, message = "your age must be between 5 and 99")
    private int age;

    public UserD(){
    }

    public UserD(String name){
        this.name = name;
    }
}
