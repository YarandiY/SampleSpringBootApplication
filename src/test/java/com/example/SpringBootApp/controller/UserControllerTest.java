package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.UserServiceTest;
import com.example.SpringBootApp.domain.UserD;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.service.UserServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userD)))
                .andExpect(status().is(500));

        userD.setName("sample");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userD)))
                .andExpect(status().is(500));

        userD.setAge(2);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userD)))
                .andExpect(status().is(500));

        userD.setPassword("123");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userD)))
                .andExpect(status().is(500));

        userD.setAge(15);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userD)))
                .andExpect(status().is(500));

        userD.setPassword("123456789");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userD)))
                .andExpect(status().is(200));
    }

    @Test
    void getUser() throws Exception {
        int userId = 3;
        given(userServiceImp.getUser(userId)).willReturn(users.get(userId));
        String json = gson.toJson(users.get(userId));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    void deleteUser() throws Exception {
        int userId = 3;
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().string("user removed"));
    }

    @Test
    void updateUser() throws Exception {
        int userId = 3;
        UserD userD = new UserD();
        userD.setName("updated!");
        User expUser = users.get(userId);
        expUser.setName("updated!");
        given(userServiceImp.updateUser(userId, userD)).willReturn(expUser);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + userId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userD)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(expUser)));
    }

    @Test
    void getUsersByName() throws Exception {
        int userId = 5;
        String userName = "user" + userId;
        List<User> expUsers = Collections.singletonList(users.get(userId));
        given(userServiceImp.getUsersByName(userName)).willReturn(expUsers);
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType("application/json")
                .param("name", userName))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(expUsers)));
    }

    @Test
    void searchByName() throws Exception {
        String userName = "user";
        given(userServiceImp.searchByName(userName)).willReturn(users);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/search")
                .contentType("application/json")
                .param("name", userName))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(users)));
    }

}