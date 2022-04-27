package com.alexsantos.sistemapedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexsantos.sistemapedidos.domain.Pedido;
import com.alexsantos.sistemapedidos.repositories.PedidoRepository;
import com.alexsantos.sistemapedidos.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id:" + id + " Tipo:" + Pedido.class.getName()));
	}
}