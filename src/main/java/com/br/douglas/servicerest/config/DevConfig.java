package com.br.douglas.servicerest.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.douglas.servicerest.services.DBService;
import com.br.douglas.servicerest.services.EmailService;
import com.br.douglas.servicerest.services.SmtpEmailService;

@Configuration
@Profile("dev"	)
public class DevConfig {

	@Autowired
	private DBService dbSrvice;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbSrvice.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
