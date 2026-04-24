package com.ntt.userDetails.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.userDetails.domain.model.UserDetails;

@Repository
public interface UsersDetailsRepository extends JpaRepository<UserDetails,Long> {

	/*public List<User> getUsers();

	public void addUser(User u);

	public void removeUser(User u);
	
	*/
}
