package com.ntt.usersManager.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.usersManager.domain.model.User;
import com.ntt.usersManager.domain.repository.UserRepository;
import com.ntt.usersManager.infrastructure.dto.UserRequestDTO;
import com.ntt.usersManager.infrastructure.dto.UserResponseDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = toEntity(dto);
        User saved = repository.save(user);
        return toDTO(saved);
    }

  
    public UserResponseDTO getUserById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.map(this::toDTO).orElse(null);
    }

   
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

   
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }


    private User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmeal(dto.getEmeal());
        return user;
    }

    private UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmeal()
        );
    }
}