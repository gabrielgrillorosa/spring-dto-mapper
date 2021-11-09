package com.gabriel.lancamentos.api.repository.projections;

import java.math.BigDecimal;

public interface LancamentoView {
	
	Long getCodigo();
	BigDecimal getValor();
	String getDescricao();

}
