package com.ntt.userDetails.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.userDetails.domain.model.UserDetails;
import com.ntt.userDetails.domain.repository.UsersDetailsRepository;
import com.ntt.userDetails.infrastructure.dto.UserDetailsRequestDTO;
import com.ntt.userDetails.infrastructure.dto.UserDetailsResponseDTO;


@Service
public class UserDetailsService {

    @Autowired
    private UsersDetailsRepository repository;

    
    public UserDetailsResponseDTO createUser(UserDetailsRequestDTO dto) {
        UserDetails user = toEntity(dto);
        UserDetails saved = repository.save(user);
        return toDTO(saved);
    }

  
    public UserDetailsResponseDTO getUserById(Long id) {
        Optional<UserDetails> optionalUser = repository.findById(id);
        return optionalUser.map(this::toDTO).orElse(null);
    }

   
    public List<UserDetailsResponseDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

   
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }


    private UserDetails toEntity(UserDetailsRequestDTO dto) {
        UserDetails user = new UserDetails();
        user.setAge(dto.getAge());
        user.setAdress(dto.getAdress());
        user.setUserId(dto.getUserId());
        return user;
    }

    private UserDetailsResponseDTO toDTO(UserDetails user) {
        return new UserDetailsResponseDTO(
                user.getId(),
                user.getUserId(),
                user.getAge(),
                user.getAdress()
        );
    }
}