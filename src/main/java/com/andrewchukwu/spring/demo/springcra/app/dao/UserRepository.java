package com.andrewchukwu.spring.demo.springcra.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrewchukwu.spring.demo.springcra.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
