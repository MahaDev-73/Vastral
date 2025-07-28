package com.sunbeam.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunbeam.config.AppConfig;
import com.sunbeam.config.JwtProvider;
import com.sunbeam.daos.CartRepository;
import com.sunbeam.daos.SellerRepository;
import com.sunbeam.daos.UserRepository;
import com.sunbeam.daos.VerificationCodeRepository;
import com.sunbeam.entities.Cart;
import com.sunbeam.entities.Seller;
import com.sunbeam.entities.User;
import com.sunbeam.entities.VerificationCode;

import com.sunbeam.models.UserRole;
import com.sunbeam.request.LoginRequest;
import com.sunbeam.response.AuthResponse;
import com.sunbeam.response.SignupRequest;
import com.sunbeam.services.AuthService;
import com.sunbeam.services.EmailService;
import com.sunbeam.utils.OtpUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AppConfig appConfig;

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final CartRepository cartRepository;
	private final JwtProvider jwtProvider;
	private final VerificationCodeRepository verificationCodeRepository;
	private final EmailService emailService;
	private final CustomerUserServiceImpl customUserService;
	private final SellerRepository sellerRepository;

	

	@Override
	public void sentLoginOtp(String email, UserRole role) throws Exception {
		String SIGNING_PREFIX = "signin_";

		if (email.startsWith(SIGNING_PREFIX)) {
			
			email = email.substring(SIGNING_PREFIX.length());

			if (role.equals(UserRole.ROLE_SELLER)) {
				Seller seller = sellerRepository.findByEmail(email);
				if (seller == null) {
					throw new Exception("Seller not found...");
				}
			} else {
				System.out.println("Email "+ email);
				User user = userRepository.findByEmail(email);
				if (user == null) {
					throw new Exception("User not exists with following email...");
				}
			}

		}

		VerificationCode isExists = verificationCodeRepository.findByEmail(email);

		if (isExists != null) { // verification code is exists
			verificationCodeRepository.delete(isExists);
		}

		String otp = OtpUtil.generateOtp();

		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setOtp(otp);
		verificationCode.setEmail(email);

		verificationCodeRepository.save(verificationCode); // save otp into database

		String subject = "Vastral : Login/Signup OTP";
		String text = "Your Login/Signup OTP is - " + otp;

		emailService.sendVerificationOtpEmail(email, otp, subject, text);

	}



//	===============

	@Override
	public String createUser(SignupRequest req) throws Exception { // if user is already exists with the provided email
																	// or not

// checking if verification code is already present in DB with this email address or not
		VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());
		
		if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
			throw new Exception("Wrong Otp...");
		}

		User user = userRepository.findByEmail(req.getEmail());

		if (user == null) {
			User createdUser = new User();
			createdUser.setEmail(req.getEmail());
			createdUser.setFullName(req.getFullName());
			createdUser.setRole(UserRole.ROLE_CUSTOMER);
			createdUser.setMobile("7887393926");
			createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

			user = userRepository.save(createdUser);

			Cart cart = new Cart();
			cart.setUser(user);
			cartRepository.save(cart);

		}

//		create authentication using granted authority
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(UserRole.ROLE_CUSTOMER.toString()));

		Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return jwtProvider.generateToken(authentication);
	}

//	==============

	@Override
	public AuthResponse signing(LoginRequest req) throws Exception {
		String username = req.getEmail();
		String otp = req.getOtp();

		Authentication authentication = authenticate(username, otp);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication); // generate jwt token

		AuthResponse authResopnse = new AuthResponse();
		authResopnse.setJwt(token);
		authResopnse.setMessage("Login Success");

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // USER_ROLE
		String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

		authResopnse.setRole(UserRole.valueOf(roleName));
		return authResopnse;
	}

	
	private Authentication authenticate(String username, String otp) throws Exception { // verify otp

		UserDetails userDetails = customUserService.loadUserByUsername(username);
		
		
		String SELLER_PREFIX = "seller_";
		if (username.startsWith(SELLER_PREFIX)) {
			username = username.substring(SELLER_PREFIX.length());
		} 
		
		
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}

		VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);
		// User Provided OTP
		if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
			throw new Exception("Wrong OTP");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
