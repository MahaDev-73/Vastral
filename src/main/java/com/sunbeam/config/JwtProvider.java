package com.sunbeam.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtProvider {
	
	SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

	public String generateToken(Authentication auth) {
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		String roles = populateAuthorities(authorities);
		
	   	 return Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date (new Date().getTime() + 86400000))
				.claim("email", auth.getName())
				.claim("authorities", roles) //provide User Role eg.Customer, Admin
				.signWith(key)
				.compact();
	   	 
	}
	
	public String getEmailFromJwtToken(String jwt) { //Get the email
		 
		jwt = jwt.substring(7); //Extract Barrer keyword from the jwt token
		 
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
					.parseClaimsJws(jwt).getBody();
		
		return String.valueOf(claims.get("email"));
	}
	

	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		
		Set<String> auths = new HashSet<>();
		
		for(GrantedAuthority authority : authorities) {
			auths.add(authority.getAuthority());
		}
		
		return String.join(",", auths);
	}
	
}
