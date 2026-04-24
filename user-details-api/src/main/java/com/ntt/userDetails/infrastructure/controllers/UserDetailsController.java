package com.ntt.userDetails.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;

import com.ntt.userDetails.application.service.UserDetailsService;
import com.ntt.userDetails.infrastructure.dto.UserDetailsRequestDTO;
import com.ntt.userDetails.infrastructure.dto.UserDetailsResponseDTO;


@RestController
@RequestMapping("/users-details")
public class UserDetailsController {

	@Autowired
	@Lazy
	private UserDetailsService usersService;

	@GetMapping
	public ResponseEntity<List<UserDetailsResponseDTO>> getUsers() {
		List<UserDetailsResponseDTO> users = usersService.getAllUsers();
		return ResponseEntity.ok(users);

	}

	@GetMapping("{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		UserDetailsResponseDTO user = usersService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<?> postUser(@RequestBody @Valid UserDetailsRequestDTO user) {
		usersService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User creado");

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		usersService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}

