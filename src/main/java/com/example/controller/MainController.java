package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class MainController {
	
	private UserRepository userRepository;
	
	@Autowired
	public MainController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping
	public User put(@RequestParam String name) {
		return userRepository.save(new User(name));
	}
	
	@GetMapping
	public Iterable<User> list() {
		return userRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public User findOne(@PathVariable Long id) {
		return userRepository.findOne(id);
	}
	
	@PostMapping(value = "/{id}")
	public User update(@PathVariable Long id, @RequestParam String name) {
		User user = userRepository.findOne(id);
		user.setName(name);
		return userRepository.save(user);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		userRepository.delete(id);
	}
}
