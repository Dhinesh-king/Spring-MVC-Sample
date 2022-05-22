package com.example.demo.controller;

import com.example.demo.dto.UserRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/user")
public class HomeController {

	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping(value = "/register")
	public String showRegistrationPage()
	{
		return "registration";
	}
	
	
	@PostMapping(value = "/register")
	public String saveData(@ModelAttribute User user,Model model)
	{
		String msg=userService.saveUser(user);
		model.addAttribute("msg",msg);
		return "registration";
	}
	
	
	@GetMapping(value = "/login")
	public String showLoginPage()
	{
		return "login";
	}

	@PostMapping(value = "/login")
	public String loginData(@ModelAttribute UserRequest userRequest,Model model) {
		boolean isValidCredentials = userService.checkLoginCredentials(
						userRequest);
		System.out.println("User name is "+ userRequest.getUsername());
		if (isValidCredentials) {
			model.addAttribute("username",userRequest.getUsername());
			return "welcome";
		}
		System.out.println("User data is Invalid");
		model.addAttribute("message", "The user credentials are invalid");
		return "login";
	}
	
}
