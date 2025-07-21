package com.sunbeam.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sunbeam.model.Customer;
import com.sunbeam.repository.CustomerRepo;

@Service
public class CustomerUserService implements UserDetailsService {
	
	@Autowired
	private CustomerRepo customerDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer> user = customerDao.findByUsername(username);
		if(user.isPresent()) {
			var userObj = user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.build();
			
		}else {
			throw new UsernameNotFoundException(username);
			
			
		}
	}
	

	
	
	
	//@Override
	//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Customer customer = customerDao.findByEmail(username);
////		Collection<String> roles = new ArrayList<String>();
////		roles.add(customer.getRole());
//		System.out.println("Customer DETAILS: "+customer);
//		
//		return new org.springframework.security.core.userdetails.User(
//				customer.getEmail(),                // username
//				customer.getPassword(),            // password (should be encoded)
//			new ArrayList<GrantedAuthority>()        // authorities or roles (as Collection<? extends GrantedAuthority>)
//        );
//		Optional<Customer> user = customerDao.findByUsername(username);
//		if(user.isPresent()) {
//			var userObj = user.get();
//			return User.builder()
//					.username(userObj.getUsername())
//					.password(userObj.getPassword())
//					.build();
//			
//		}else {
//			throw new UsernameNotFoundException(username);
//			
//			
//		}
//		throw new UnsupportedOperationException("Unimplemented method 'LoaduserByUsername'");
//		
//}
	}

























	


