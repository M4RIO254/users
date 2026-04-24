package com.ntt.usersOrchestrator.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.ntt.usersOrchestrator.domain.repository.UserDetailsRepository;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserDetailsRequestDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserDetailsResponseDTO;

@Repository
public class UserDetailsRepositoryImpl implements UserDetailsRepository {

    private final WebClient webClient;

    private final String BASE_URL = "http://localhost:8081/users-details";

    public UserDetailsRepositoryImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<UserDetailsResponseDTO> findAll() {
        return webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToFlux(UserDetailsResponseDTO.class)
                .collectList()
                .block();
    }

    @Override
    public UserDetailsResponseDTO findByUserId(Long userId) {
        return webClient.get()
                .uri(BASE_URL + "/" + userId)
                .retrieve()
                .bodyToMono(UserDetailsResponseDTO.class)
                .block();
    }

    @Override
    public void save(UserDetailsRequestDTO request) {
        webClient.post()
                .uri(BASE_URL)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}