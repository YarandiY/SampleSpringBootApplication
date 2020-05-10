package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.UserServiceTest;
import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.service.UserServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ContextConfiguration(classes = SpringBootApplication.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImp userServiceImp;

    @Autowired
    ObjectMapper objectMapper;

    private static List<User> users;
    private static Gson gson;

    @BeforeAll
    public static void beforeAll() {
        users = UserServiceTest.createUsers();
        gson = new Gson();
    }

    @Test
    void getUsers() throws Exception {
        String json = gson.toJson(users);
        given(userServiceImp.getUsers()).willReturn(users);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    void addUser() throws Exception {
        UserD userD = new UserD();
        userD.setName("sample");
        userD.setAge(12);
        userD.setPassword("$sample$");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userD)))
                .andExpect(status().isOk());
    }

}