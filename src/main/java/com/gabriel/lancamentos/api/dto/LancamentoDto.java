package com.gabriel.lancamentos.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.gabriel.lancamentos.api.model.Categoria;
import com.gabriel.lancamentos.api.model.Pessoa;
import com.gabriel.lancamentos.api.model.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class LancamentoDto {
	
	private Long codigo;
	
	@NotNull
	private String descricao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPagamento;

	@NotNull
	private BigDecimal valor;

	private String observacao;

	private TipoLancamento tipo;

	private Categoria categoria;

	private Pessoa pessoa;

}
