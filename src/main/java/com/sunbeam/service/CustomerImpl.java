//package com.sunbeam.service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.sunbeam.model.Customer;
//import com.sunbeam.repository.CustomerRepo;
//import com.sunbeam.response.Credentials;
//import com.sunbeam.response.CustomerResponse;
//
//@Service
//public class CustomerImpl implements CustomerService {
//	private PasswordEncoder encoder;
//	private CustomerRepo customerDao;
//	
//	@Autowired
//	public CustomerImpl(CustomerRepo userDao, PasswordEncoder encoder ){ 
//		this.encoder= encoder;
//		this.customerDao = userDao;
//	}
//
//	
//
//	@Override
//	public CustomerResponse<String> authenticate(Credentials customer) {
//		Optional<Customer> cust = customerDao.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
//		CustomerResponse<String> resp = new CustomerResponse();
//		if(cust.isPresent()) {
//			return resp.success("Successfully Authenticated");
//		}
//		return resp.error("Not Authorised");
//	}
//
//	@Override
//	public void deleteById(int id) {
//		customerDao.deleteById(id);
//		
//	}
//
//	@Override
//	public List<Customer> findAll() {
//		return customerDao.findAll();
//	}
//
//	@Override
//	public CustomerResponse<String> save(Customer customer) {
//		CustomerResponse<String> resp = new CustomerResponse();
//		
//		try {
//		String encodedPassword = encoder.encode(customer.getPassword());
//		customer.setPassword(encodedPassword);
//		Customer cust = customerDao.save(customer);
//		return resp.success("Hey you are register");
//		
//		
//		}catch (Exception e) {
//			return resp.error(e.getMessage());
//		}
//			
//	}
//
//		
//		
//	}
//
//
//
//
//
//
//	
//
//	
//
//
