package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode , Long>{

//					findBy + fieldname(eg. Email, otp)
	VerificationCode findByEmail(String email);
	VerificationCode findByOtp(String otp);
	
}
