package com.ntt.usersOrchestrator.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntt.usersOrchestrator.infrastructure.dtos.UserRequestDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserResponseDTO;
@Repository
public interface UserRepository {

    List<UserResponseDTO> findAll();

    UserResponseDTO findById(Long id);

    UserResponseDTO save(UserRequestDTO request);

    void delete(Long id);
}