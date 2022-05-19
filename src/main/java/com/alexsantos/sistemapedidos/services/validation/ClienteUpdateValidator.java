package com.alexsantos.sistemapedidos.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.alexsantos.sistemapedidos.domain.Cliente;
import com.alexsantos.sistemapedidos.dto.ClienteDTO;
import com.alexsantos.sistemapedidos.repositories.ClienteRepository;
import com.alexsantos.sistemapedidos.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		//Método que me permite mapear o id que está sendo informado na URI, e compará-lo com o id que está sendo alterado...
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt( map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		//se o Id for diferente de nulo ee o id que esta sendo alterado tiver email idêntico a um email 
		//já existente em outro Id, então lança msg de que email já existe em outro Cliente...
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux !=null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "O Email inserido já está cadastrado!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}