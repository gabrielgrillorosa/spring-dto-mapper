package com.gabriel.lancamentos.api.service;

import java.util.Optional;

import com.gabriel.lancamentos.api.exeception.PessoaInexistenteOuInativaException;
import com.gabriel.lancamentos.api.model.Lancamento;
import com.gabriel.lancamentos.api.model.Pessoa;
import com.gabriel.lancamentos.api.repository.LancamentoRepository;
import com.gabriel.lancamentos.api.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired 
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (pessoa.isEmpty() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}
	
}
