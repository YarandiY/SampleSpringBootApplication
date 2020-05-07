package com.example.SpringBootApp.model;

import com.example.SpringBootApp.domain.UserD;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "users")
@Embeddable
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(min = 2, max = 10)
    private String name;
    @NotNull
    @Size(min = 6, max = 10)
    private String password;
    @Range(min = 5, max = 99)
    private int age;

    public User(){

    }
    public User(UserD userD){
        this.name = userD.getName();
        this.password = userD.getPassword();
        this.age = userD.getAge();

    }
}