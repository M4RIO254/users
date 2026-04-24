package com.ntt.usersOrchestrator.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntt.usersOrchestrator.infrastructure.dtos.UserDetailsRequestDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserDetailsResponseDTO;
@Repository
public interface UserDetailsRepository {

    List<UserDetailsResponseDTO> findAll();

    UserDetailsResponseDTO findByUserId(Long userId);

    void save(UserDetailsRequestDTO request);
}