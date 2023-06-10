   package com.tripplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripplanner.model.User;
import com.tripplanner.service.UserInfoService;

@CrossOrigin(origins = "http://localhost:3000" , maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserInfoController {
	
	@Autowired
	UserInfoService userInfoService;
	
	
	@RequestMapping
	public List<User> getAllUsers(){
		return userInfoService.fetchAllUsers();
	}
	
	@PostMapping
    public User createUser(@RequestBody User user) {
        return userInfoService.saveUser(user);
    }
	
	 	@GetMapping("{email}")
	    public ResponseEntity<User> getUserById(@PathVariable  Long id){
	        User User = userInfoService.findById(id)
	        		.orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
	        return ResponseEntity.ok(User);
	    }

	    // build update User REST API
	    @PutMapping("{id}")
	    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User UserDetails) {
	        User updateUser = userInfoService.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

	        updateUser.setName(UserDetails.getName());
	        updateUser.setEmail(UserDetails.getEmail());

	        userInfoService.saveUser(updateUser);

	        return ResponseEntity.ok(updateUser);
	    }

	    // build delete User REST API
	    @DeleteMapping("{id}")
	    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){

	        User user = userInfoService.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

	        userInfoService.deleteUser(user);

	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	    }
	
	

}
