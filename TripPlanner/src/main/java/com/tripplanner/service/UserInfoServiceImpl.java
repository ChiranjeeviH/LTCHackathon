package com.tripplanner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripplanner.model.User;
import com.tripplanner.repository.UserRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserRepository userRepository;
	
	public UserInfoServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	/*
	 * @Override public User getUserByEmailId(String email) {
	 * 
	 * return userRepository.getUserByEmailId(email); }
	 */

	@Override
	public List<User> fetchAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	/*
	 * @Override public User deleteUserById(String email) { return
	 * userRepository.deleteUserByEmailId(email); }
	 */


	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}
