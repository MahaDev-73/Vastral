//package com.sunbeam.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.sunbeam.service.CustomerImpl;
//
//
//
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//	
//	
//	
//	@Autowired
//	private JwtFilter jwtFilter;
//	
//	@Autowired
//	private CustomerUserService customerUserSevice;
//	
//
//	
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	AuthenticationManager authenticationManager(HttpSecurity http,CustomerUserService customerService) throws Exception {
//		AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//		authManagerBuilder.userDetailsService(customerService);
//		return authManagerBuilder.build();
//	}
//	
//	@Bean
//	SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
//		http
//			.csrf(csrf -> csrf.disable())
//			.authorizeHttpRequests(requests -> 
//			 requests 
//			
//				.requestMatchers("/login").permitAll()
//				.requestMatchers("/register").permitAll()
//				.requestMatchers("/customer/authenticate").permitAll()
//				.requestMatchers("/cust/**").hasAuthority("CUSTOMER")
//				.requestMatchers("/admin/**").hasRole("ADMIN")
//		    	.anyRequest().authenticated()
//		    )
//			.httpBasic(Customizer.withDefaults())
//			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		return http.build();
//	}
//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customerUserSevice); // use your class
//        provider.setPasswordEncoder(passwordEncoder()); // use BCrypt
//        return provider;
//    }
//	
//}
