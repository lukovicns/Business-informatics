package com.project.Businessinformatics.security;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.Businessinformatics.model.user.Client;
import com.project.Businessinformatics.model.user.User;
import com.project.Businessinformatics.service.ClientService;
import com.project.Businessinformatics.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
public class JwtValidator {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClientService clientService;
	
	Key key = MacProvider.generateKey();
	
    public User validateUser(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return userService.findByIdAndEmail(Long.parseLong(body.get("id").toString()), (String) body.get("email"));
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public Client validateClient(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return clientService.findByIdAndEmail(Long.parseLong(body.get("id").toString()), (String) body.get("email"));
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
