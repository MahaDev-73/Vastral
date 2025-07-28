package com.sunbeam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	@Autowired
	private final JavaMailSender javaMailSender;
	
	public void sendVerificationOtpEmail(String userEmail,
										 String otp, String subject, 
										 String text) throws MessagingException {
		
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage, "utf-8");
			
			
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(text, false);
			mimeMessageHelper.setTo(userEmail);
			javaMailSender.send(mimeMessage);
			
		}
		catch(MailException e) {
			System.out.println("Sending OTP to: " + userEmail + ", OTP: " + otp);
			throw new MailSendException("Failed to Send Email...");
		} 
		
	}
	
}
