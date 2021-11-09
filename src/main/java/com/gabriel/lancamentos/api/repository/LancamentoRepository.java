package com.gabriel.lancamentos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.lancamentos.api.model.Lancamento;
import com.gabriel.lancamentos.api.model.TipoLancamento;
import com.gabriel.lancamentos.api.repository.lancamento.LancamentoRepositoryQuery;
import com.gabriel.lancamentos.api.repository.projections.LancamentoView;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
	List<LancamentoView>  getValorByTipo(TipoLancamento tipo);
    
}
