package com.ntt.usersManager.infrastructure.repository;
/*
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ntt.usersManager.domain.model.User;
import com.ntt.usersManager.domain.repository.UserRepository;

import com.ntt.usersManager.infrastructure.utils.exceptions.UsersReadException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "service.users", havingValue = "json")
public class UserServiceJSONImpl implements UserRepository {

	private List<User> users = readUsers();

	public List<User> readUsers() {

		List<User> usersTemp;

		try {

			usersTemp = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/users.json"),
					new TypeReference<List<User>>() {
					});

			return usersTemp;

		} catch (Exception e) {

			throw new UsersReadException("Error al leer el archivo users.json", e);

		}

	}

	@Override
	public List<User> getUsers() {

		return users;
	}

	@Override
	public void addUser(User u) {
		getUsers().add(u);

	}

	@Override
	public void removeUser(User u) {
		getUsers().remove(u);

	}

}*/