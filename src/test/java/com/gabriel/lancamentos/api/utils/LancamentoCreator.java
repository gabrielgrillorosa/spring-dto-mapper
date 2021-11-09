package com.gabriel.lancamentos.api.utils;

import java.time.LocalDate;

import com.gabriel.lancamentos.api.dto.LancamentoDto;
import com.gabriel.lancamentos.api.model.Categoria;
import com.gabriel.lancamentos.api.model.Lancamento;

public class LancamentoCreator {
	
	public  static Lancamento criarLancamentoValidoSalvo() {
		return Lancamento.builder()
				.dataPagamento(LocalDate.of(2021, 11, 2))
				.dataVencimento(LocalDate.of(2021, 11, 2))
				.descricao("Descricao pagamento")
				.pessoa(PessoaCreator.pessoaValidaParaSerSalva())
				.categoria(new Categoria("Categoria lancamento"))
				.build();
	}
	
	public  static LancamentoDto criarLancamentoParaSerSalvo() {
		return LancamentoDto.builder()
				.dataPagamento(LocalDate.of(2021, 11, 2))
				.dataVencimento(LocalDate.of(2021, 11, 2))
				.descricao("Descricao pagamento")
				.pessoa(PessoaCreator.pessoaValidaParaSerSalva())
				.categoria(new Categoria("Categoria lancamento"))
				.build();
	}
	
	
	

}
