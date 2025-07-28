package com.sunbeam.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class JwtTokenValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader("Authorization");
		
//		Barrer jwt (Extract Barrer keyword to get jwt token)

		if(jwt != null) {
			jwt = jwt.substring(7);
			try {
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//		Claimns provided when JWT Token generated
				Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
						.parseClaimsJws(jwt).getBody(); //Validate the JWT token
			
				String email = String.valueOf(claims.get("email"));
				String authorities = String.valueOf(claims.get("authorities"));
				
				List<GrantedAuthority> auths = AuthorityUtils
						.commaSeparatedStringToAuthorityList(authorities);
				
//																					(password is provided as null)
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths); //Access email and authorities. eg. Role of the user.
				SecurityContextHolder.getContext().setAuthentication(authentication);  //Happen when the user is logged in (SecurityContextHolder)
				
				
			}
			catch (Exception e) {
				throw new BadCredentialsException("Invalid JWT token..");
			}
		}	
	
	
		filterChain.doFilter(request, response);
		
	}
}
