package com.alexsantos.sistemapedidos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexsantos.sistemapedidos.domain.Categoria;
import com.alexsantos.sistemapedidos.domain.Cidade;
import com.alexsantos.sistemapedidos.domain.Cliente;
import com.alexsantos.sistemapedidos.domain.Endereco;
import com.alexsantos.sistemapedidos.domain.Estado;
import com.alexsantos.sistemapedidos.domain.Produto;
import com.alexsantos.sistemapedidos.domain.enums.TipoCliente;
import com.alexsantos.sistemapedidos.repositories.CategoriaRepository;
import com.alexsantos.sistemapedidos.repositories.CidadeRepository;
import com.alexsantos.sistemapedidos.repositories.ClienteRepository;
import com.alexsantos.sistemapedidos.repositories.EnderecoRepository;
import com.alexsantos.sistemapedidos.repositories.EstadoRepository;
import com.alexsantos.sistemapedidos.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaPedidosApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000);
		Produto p2 = new Produto(null, "Impressora a laser", 1300);
		Produto p3 = new Produto(null, "Mouse óptico", 50);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Alex Santos","lequinsantos@gmail.com","39735380854",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("19991247679","1936413060"));
		
		Endereco e1 = new Endereco(null, "Rua Benedito da Silva","305","casa","Jd S.Jose","13880000",cli1,c2);
		
		Endereco e2 = new Endereco(null, "Rua Benedito de Oliveira","200","apto 1","Jd das flores","13880980",cli1,c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		

	}
}