package com.ntt.usersOrchestrator.application.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.usersOrchestrator.domain.repository.UserDetailsRepository;
import com.ntt.usersOrchestrator.domain.repository.UserRepository;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserDetailsRequestDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserDetailsResponseDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserFullRequestDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserFullResponseDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserRequestDTO;
import com.ntt.usersOrchestrator.infrastructure.dtos.UserResponseDTO;

@Service
public class OrchestratorService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public List<UserFullResponseDTO> getAll() {

		List<UserResponseDTO> users = userRepository.findAll();
		List<UserDetailsResponseDTO> details = userDetailsRepository.findAll();

		return users.stream().map(user -> {
			Optional<UserDetailsResponseDTO> detailOpt = details.stream().filter(d -> d.getUserId() != null)
					.filter(d -> d.getUserId().longValue() == user.getId().longValue()).findFirst();

			return map(user, detailOpt.orElse(null));
		}).collect(Collectors.toList());
	}

	public UserFullResponseDTO getById(Long id) {

		UserResponseDTO user = userRepository.findById(id);
		UserDetailsResponseDTO detail = userDetailsRepository.findByUserId(id);

		return map(user, detail);
	}

	public UserFullResponseDTO create(UserFullRequestDTO request) {

		UserResponseDTO user = userRepository.save(new UserRequestDTO(request.getName(), request.getEmail()));

		userDetailsRepository.save(new UserDetailsRequestDTO(user.getId(), request.getAge(), request.getAdress()

		));

		return getById(user.getId());
	}

	public void delete(Long id) {
		userRepository.delete(id);
	}

	private UserFullResponseDTO map(UserResponseDTO user, UserDetailsResponseDTO detail) {
		UserFullResponseDTO res = new UserFullResponseDTO();

		res.setId(user.getId());
		res.setName(user.getName());
		res.setEmail(user.getEmeal());

		if (detail != null) {
			res.setAdress(detail.getAdress());
			res.setAge(detail.getAge());
		}

		return res;
	}
}