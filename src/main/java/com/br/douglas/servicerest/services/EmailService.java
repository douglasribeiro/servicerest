package com.br.douglas.servicerest.services;

import org.springframework.mail.SimpleMailMessage;

import com.br.douglas.servicerest.domain.Pedido;


public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}