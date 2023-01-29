package com.andrewchukwu.spring.demo.springcra.app.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrewchukwu.spring.demo.springcra.app.dao.UserRepository;
import com.andrewchukwu.spring.demo.springcra.app.entity.User;

@RestController
	@RequestMapping("/users")
	class UsersController {
Ï

	    private final UserRepository userRepository;

	    public UsersController(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @GetMapping
	    public List<User> getUsers() {
	        return userRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public User getUser(@PathVariable Long id) {
	        return userRepository.findById(id).orElseThrow(RuntimeException::new);
	    }

	    @PostMapping
	    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
	    	User savedUser = userRepository.save(user);
	        return ResponseEntity.created(new URI("/users/" + savedUser.getId())).body(savedUser);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody User user) {
	        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
	        currentUser.setFirstName(user.getFirstName());
	        currentUser.setLastName(user.getLastName());
	        currentUser.setEmailAddress(user.getEmailAddress());
	        currentUser = userRepository.save(user);
	        return ResponseEntity.ok(currentUser);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity deleteUser(@PathVariable Long id) {
	    	userRepository.deleteById(id);
	        return ResponseEntity.ok().build();
	    }
	}
