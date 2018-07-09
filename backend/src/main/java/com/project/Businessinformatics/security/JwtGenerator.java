package com.project.Businessinformatics.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import com.project.Businessinformatics.model.user.Client;
import com.project.Businessinformatics.model.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
public class JwtGenerator {
	
	Key key = MacProvider.generateKey();
	
	public String generateUser(User user) {
		Claims claims = Jwts.claims();
		claims.put("id", user.getId());
		claims.put("email", user.getEmail());
		return Jwts.builder()
				  .setClaims(claims)
				  .signWith(SignatureAlgorithm.HS512, key)
				  .compact();
	}

	public String generateClient(Client client) {
		Claims claims = Jwts.claims();
		claims.put("id", client.getId());
		claims.put("email", client.getEmail());
		return Jwts.builder()
				  .setClaims(claims)
				  .signWith(SignatureAlgorithm.HS512, key)
				  .compact();
	}
}
