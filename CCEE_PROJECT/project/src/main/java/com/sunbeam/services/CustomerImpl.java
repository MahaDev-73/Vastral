package com.sunbeam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunbeam.entities.Customer;

import com.sunbeam.models.Credentials;
import com.sunbeam.models.CustomerResponse;
import com.sunbeam.repository.CustomerRepo;

@Service
public class CustomerImpl implements CustomerService , UserDetailsService {
	private PasswordEncoder encoder;
	private CustomerRepo customerDao;
	
	@Autowired
	public CustomerImpl(CustomerRepo userDao, PasswordEncoder encoder ){ 
		this.encoder= encoder;
		this.customerDao = userDao;
	}

	

	@Override
	public CustomerResponse<String> authenticate(Credentials customer) {
		Optional<Customer> cust = customerDao.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
		CustomerResponse<String> resp = new CustomerResponse();
		if(cust.isPresent()) {
			return resp.success("Successfully Authenticated");
		}
		return resp.error("Not Authorised");
	}

	@Override
	public void deleteById(int id) {
		customerDao.deleteById(id);
		
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public CustomerResponse<String> save(Customer customer) {
		CustomerResponse<String> resp = new CustomerResponse();
		
		try {
		String encodedPassword = encoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
		Customer cust = customerDao.save(customer);
		return resp.success("Hey you are register");
		
		
		}catch (Exception e) {
			return resp.error(e.getMessage());
		}
			
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return null;
	}



	

	

}
