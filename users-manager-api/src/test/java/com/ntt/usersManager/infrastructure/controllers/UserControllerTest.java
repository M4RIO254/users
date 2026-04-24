package com.ntt.usersManager.infrastructure.controllers;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.usersManager.application.service.UserService;
import com.ntt.usersManager.infrastructure.dto.UserRequestDTO;
import com.ntt.usersManager.infrastructure.dto.UserResponseDTO;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void shouldGetUserById_ReturnsOk() throws Exception {

        UserResponseDTO response = new UserResponseDTO(1L, "John", "john@email.com");

        Mockito.when(userService.getUserById(1L)).thenReturn(response);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.emeal").value("john@email.com"));
    }

    @Test
    void shouldGetUserById_Returns404_WhenNotFound() throws Exception {

        Mockito.when(userService.getUserById(1L)).thenReturn(null);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetUserById_Returns500_WhenException() throws Exception {

        Mockito.when(userService.getUserById(1L))
                .thenThrow(new RuntimeException());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isInternalServerError());
    }


    @Test
    void shouldGetAllUsers_ReturnsOk() throws Exception {

        List<UserResponseDTO> users = Arrays.asList(
                new UserResponseDTO(1L, "John", "john@email.com"),
                new UserResponseDTO(2L, "Jane", "jane@email.com")
        );

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldGetAllUsers_Returns500_WhenException() throws Exception {

        Mockito.when(userService.getAllUsers())
                .thenThrow(new RuntimeException());

        mockMvc.perform(get("/users"))
                .andExpect(status().isInternalServerError());
    }



    @Test
    void shouldCreateUser_ReturnsOk() throws Exception {

        UserRequestDTO request = new UserRequestDTO();
        request.setName("John");
        request.setEmeal("john@email.com");

        UserResponseDTO response = new UserResponseDTO(1L, "John", "john@email.com");

        Mockito.when(userService.createUser(any())).thenReturn(response);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void shouldCreateUser_Returns400_WhenInvalidInput() throws Exception {

        UserRequestDTO request = new UserRequestDTO();
        request.setName(""); 
        request.setEmeal("");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateUser_Returns500_WhenException() throws Exception {

        UserRequestDTO request = new UserRequestDTO();
        request.setName("John");
        request.setEmeal("john@email.com");

        Mockito.when(userService.createUser(any()))
                .thenThrow(new RuntimeException());

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }


    @Test
    void shouldDeleteUser_ReturnsOk() throws Exception {

        Mockito.doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteUser_Returns500_WhenException() throws Exception {

        Mockito.doThrow(new RuntimeException())
                .when(userService).deleteUser(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isInternalServerError());
    }
}