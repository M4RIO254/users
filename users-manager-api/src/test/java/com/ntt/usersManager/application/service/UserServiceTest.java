package com.ntt.usersManager.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ntt.usersManager.application.service.UserService;
import com.ntt.usersManager.domain.model.User;
import com.ntt.usersManager.domain.repository.UserRepository;
import com.ntt.usersManager.infrastructure.dto.UserRequestDTO;
import com.ntt.usersManager.infrastructure.dto.UserResponseDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;


    @Test
    void shouldCreateUser() {

        UserRequestDTO request = new UserRequestDTO();
        request.setName("Juan");
        request.setEmeal("juan@mail.com");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Juan");
        savedUser.setEmeal("juan@mail.com");

        when(repository.save(any())).thenReturn(savedUser);

        UserResponseDTO response = userService.createUser(request);

        assertNotNull(response);
        assertEquals("Juan", response.getName());
        assertEquals("juan@mail.com", response.getEmeal());

        verify(repository).save(any());
    }
    
    
    @Test
    void shouldThrowExceptionWhenCreateUserFails() {

        UserRequestDTO request = new UserRequestDTO();
        request.setName("Juan");
        request.setEmeal("juan@mail.com");

        when(repository.save(any()))
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(request);
        });
    }

 
    @Test
    void shouldReturnUserById() {

        User user = new User();
        user.setId(1L);
        user.setName("Ana");
        user.setEmeal("ana@mail.com");

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDTO response = userService.getUserById(1L);

        assertNotNull(response);
        assertEquals("Ana", response.getName());
    }

   
    @Test
    void shouldReturnNullWhenUserNotFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        UserResponseDTO response = userService.getUserById(1L);

        assertNull(response);
    }
    
    @Test
    void shouldThrowExceptionWhenGetUserByIdFails() {

        when(repository.findById(1L))
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1L);
        });
    }

    @Test
    void shouldReturnAllUsers() {

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Juan");
        user1.setEmeal("juan@mail.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Ana");
        user2.setEmeal("ana@mail.com");

        when(repository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserResponseDTO> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("Juan", users.get(0).getName());
    }
    
    @Test
    void shouldThrowExceptionWhenGetAllUsersFails() {

        when(repository.findAll())
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> {
            userService.getAllUsers();
        });
    }

    
    @Test
    void shouldDeleteUser() {

        doNothing().when(repository).deleteById(1L);

        userService.deleteUser(1L);

        verify(repository).deleteById(1L);
    }
}