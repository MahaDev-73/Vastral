
package com.sunbeam.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunbeam.daos.UserRepository;
import com.sunbeam.entities.Credentials;
import com.sunbeam.entities.User;
import com.sunbeam.response.UserResponse;
import com.sunbeam.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userDao;
	@Autowired
	private PasswordEncoder encoder;
	
	public UserServiceImpl(UserRepository userDao,PasswordEncoder encoder) {
		userDao = this.userDao;
		encoder = this.encoder;
	}

	

	@Override
	public User getCustomerByEmail(String email) {
		User dbCust = userDao.findByEmail(email);
		return dbCust;
	}

	@Override
	public User getCustomerByCredentials(Credentials cr) {
		User dbCust = userDao.findByEmail(cr.getEmail());
		if(dbCust != null && dbCust.getPassword().equals(cr.getPassword()))
			return dbCust;
		
		return null;
	}
	
	@Override
	public User loadUserByUsername(String email) {
		User dbUser = userDao.findByEmail(email);
		if(dbUser == null)
			throw new UsernameNotFoundException("No user exists!");
		return dbUser;
		
	}



	@Override
	public UserResponse<String> save(User users) {
	    try {
	        String encodedPassword = encoder.encode(users.getPassword());
	        users.setPassword(encodedPassword);

	        User savedUser = userDao.save(users);

	        return UserResponse.success("Hey, you are registered");
	    } catch (Exception e) {
	        return UserResponse.error("Registration failed: " + e.getMessage());
	    }
	}



	@Override
	public User findUserProfileByJwt(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}



}