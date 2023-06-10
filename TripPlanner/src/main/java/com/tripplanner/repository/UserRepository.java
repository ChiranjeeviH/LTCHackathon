package com.tripplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripplanner.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	//public User getUserByEmailId(String email);
	
	//public User deleteUserByEmailId(String email);
	
	
	

}
