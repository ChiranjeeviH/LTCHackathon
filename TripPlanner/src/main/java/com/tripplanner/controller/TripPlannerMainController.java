package com.tripplanner.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TripPlannerMainController {
	
	@RequestMapping("/home")
	public String home() {
		return "Welcome to the Trip planner";
	}
	
	

}
