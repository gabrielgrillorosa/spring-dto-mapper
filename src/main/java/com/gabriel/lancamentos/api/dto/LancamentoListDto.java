
	package com.gabriel.lancamentos.api.dto;

	import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.gabriel.lancamentos.api.model.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class LancamentoListDto {
	
	private Long codigo;	
	
	@NotNull
	private String descricao;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataVencimento;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataPagamento;

	private BigDecimal valor;

	private String observacao;

	private TipoLancamento tipo;

	private String categoria;

	private String pessoa;

}
