package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	
}
