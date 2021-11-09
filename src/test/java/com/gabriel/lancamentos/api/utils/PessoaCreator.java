package com.gabriel.lancamentos.api.utils;
import com.gabriel.lancamentos.api.model.Endereco;
import com.gabriel.lancamentos.api.model.Pessoa;

public class PessoaCreator {
	
	public static Pessoa  pessoaValidaSalva() {
		
		Endereco endereco = Endereco.builder()
				.logradouro("Rua Itapemirim")
				.numero("17")
				.cep("29148200")
				.bairro("Vila Capixaba")
				.cidade("Cariacica")
				.estado("ES")
				.build();
		
		return Pessoa.builder()
				.codigo(1l)
				.nome("Gabriel Grillo Rosa")
				.ativo(true)
				.endereco(endereco)
				.build();
	}
	
	public static Pessoa  pessoaValidaParaSerSalva() {
		
		Endereco endereco = Endereco.builder()
				.logradouro("Rua Itapemirim")
				.numero("17")
				.cep("29148200")
				.bairro("Vila Capixaba")
				.cidade("Cariacica")
				.estado("ES")
				.build();
		
		return Pessoa.builder()
				.nome("Gabriel Grillo Rosa")
				.ativo(true)
				.endereco(endereco)
				.build();
	}

	
	public static Pessoa  pessoaAtivoFalseSalva() {
		
		Endereco endereco = Endereco.builder()
				.logradouro("Rua Itapemirim")
				.numero("17")
				.cep("29148200")
				.bairro("Vila Capixaba")
				.cidade("Cariacica")
				.estado("ES")
				.build();
		
		return Pessoa.builder()
				.codigo(1l)
				.nome("Gabriel Grillo Rosa")
				.ativo(false)
				.endereco(endereco)
				.build();
	}
	
	
	public static Pessoa  pessoaAtivoTrueSalva() {
		
		Endereco endereco = Endereco.builder()
				.logradouro("Rua Itapemirim")
				.numero("17")
				.cep("29148200")
				.bairro("Vila Capixaba")
				.cidade("Cariacica")
				.estado("ES")
				.build();
		
		return Pessoa.builder()
				.codigo(1l)
				.nome("Gabriel Grillo Rosa")
				.ativo(true)
				.endereco(endereco)
				.build();
	}
}
