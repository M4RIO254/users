package com.ntt.usersManager.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.usersManager.domain.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	/*public List<User> getUsers();

	public void addUser(User u);

	public void removeUser(User u);
	
	*/
}
