package com.tripplanner.service;

import java.util.List;
import java.util.Optional;

import com.tripplanner.model.User;

public interface UserInfoService {
	
	//public User getUserByEmailId(String email);
	
	public List<User> fetchAllUsers();
	
	public User saveUser(User user);
	
	public User updateUser(User user);
	
	//public User deleteUserById(String email);
	
	public Optional<User> findById(Long id);
	
	public void deleteUser(User user);

}
