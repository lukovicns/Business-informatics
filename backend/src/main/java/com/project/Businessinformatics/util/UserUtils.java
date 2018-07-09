package com.project.Businessinformatics.util;

import com.project.Businessinformatics.service.ClientService;
import com.project.Businessinformatics.service.UserService;

public class UserUtils {

	public boolean userExists(String email, UserService userService, ClientService service) {
		if (userService.getUserByEmail(email) != null || service.getClientByEmail(email) != null) {
			return true;
		}
		return false;
	}
}
