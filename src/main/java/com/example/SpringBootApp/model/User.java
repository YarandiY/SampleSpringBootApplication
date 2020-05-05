package com.example.SpringBootApp.model;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "Users")
@Embeddable
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    public User(){

    }
}