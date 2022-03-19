package com.alexsantos.sistemapedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexsantos.sistemapedidos.domain.Categoria;
import com.alexsantos.sistemapedidos.repositories.CategoriaRepository;
import com.alexsantos.sistemapedidos.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id:" + id + " Tipo:" + Categoria.class.getName()));
	}
}