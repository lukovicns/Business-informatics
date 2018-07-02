package com.project.Businessinformatics.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import com.project.Businessinformatics.model.Klijent;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
public class JwtGenerator {
	
	Key key = MacProvider.generateKey();
	
	public String generate(Klijent klijent) {
		Claims claims = Jwts.claims();
		claims.put("id", klijent.getId());
		claims.put("email", klijent.getEmail());
		return Jwts.builder()
				  .setClaims(claims)
				  .signWith(SignatureAlgorithm.HS512, key)
				  .compact();
	}
}
