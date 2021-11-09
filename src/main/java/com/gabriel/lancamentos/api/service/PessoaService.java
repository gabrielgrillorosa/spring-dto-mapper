package com.gabriel.lancamentos.api.service;


import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.gabriel.lancamentos.api.model.Pessoa;
import com.gabriel.lancamentos.api.repository.PessoaRepository;


@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}

	public Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		pessoaSalva.setAtivo(ativo);
		return pessoaRepository.save(pessoaSalva);
	}
	
	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		return pessoaRepository.findById(codigo).orElseThrow( () -> new EmptyResultDataAccessException(1));
		
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		return  this.pessoaRepository.save(pessoa);		
		
	}
	
	public List<Pessoa> listart() {
		return  this.pessoaRepository.findAll();		
		
	}

	
}
