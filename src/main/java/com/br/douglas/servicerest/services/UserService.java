package com.br.douglas.servicerest.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.br.douglas.servicerest.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}