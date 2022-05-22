package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository urepo;
	
	public String saveUser(User user) {
		
		User savedUser=urepo.save(user);
		String msg="User With ID :"+savedUser.getUid()+" Registered Successfully";
		return msg;
	}
	
	public boolean checkLoginCredentials(UserRequest userRequest){
		Optional<User> userOptional= urepo.findByUsername(userRequest.getUsername());
		String password = userRequest.getPassword();
		
		if(userOptional.isEmpty()){
			System.out.println("User not Found");
			return false;
		}
		else if(userOptional.get().getPassword().equals(password)){
			System.out.println("Password is correct");
			return true;
		}
		else{
			System.out.println("Password is wrong");
			return false;
		}
	}
}
