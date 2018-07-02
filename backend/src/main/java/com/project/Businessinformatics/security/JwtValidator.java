package com.project.Businessinformatics.security;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.Businessinformatics.model.Klijent;
import com.project.Businessinformatics.service.KlijentService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
public class JwtValidator {

	@Autowired
	private KlijentService service;
	Key key = MacProvider.generateKey();
	
    public Klijent validate(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return service.findByIdAndEmail(Long.parseLong(body.get("id").toString()), (String) body.get("email"));
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
