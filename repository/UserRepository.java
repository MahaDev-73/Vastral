package com.sunbeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
