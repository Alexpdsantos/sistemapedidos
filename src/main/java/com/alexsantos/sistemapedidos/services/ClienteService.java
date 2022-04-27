package com.alexsantos.sistemapedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexsantos.sistemapedidos.domain.Cliente;
import com.alexsantos.sistemapedidos.repositories.ClienteRepository;
import com.alexsantos.sistemapedidos.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id:" + id + " Tipo:" + Cliente.class.getName()));
	}
}