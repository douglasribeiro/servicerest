package com.br.douglas.servicerest.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.br.douglas.servicerest.domain.enus.TipoCliente;
import com.br.douglas.servicerest.dto.ClienteNewDto;
import com.br.douglas.servicerest.resources.exception.FieldMessage;
import com.br.douglas.servicerest.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCfpOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF invalido."));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCfpOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ invalido."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		} 
		return list.isEmpty();
	}
}
