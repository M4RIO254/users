package com.ntt.usersOrchestrator.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.ntt.usersOrchestrator.domain.repository.UserRepository;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserRequestDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserResponseDTO;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final WebClient webClient;

    private final String BASE_URL = "http://localhost:8080/users";

    public UserRepositoryImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToFlux(UserResponseDTO.class)
                .collectList()
                .block();
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return webClient.get()
                .uri(BASE_URL + "/" + id)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();
    }

    @Override
    public UserResponseDTO save(UserRequestDTO request) {
        return webClient.post()
                .uri(BASE_URL)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();
    }

    @Override
    public void delete(Long id) {
        webClient.delete()
                .uri(BASE_URL + "/" + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}