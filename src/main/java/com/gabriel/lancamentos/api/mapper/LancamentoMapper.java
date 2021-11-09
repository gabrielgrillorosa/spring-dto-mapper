package com.gabriel.lancamentos.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gabriel.lancamentos.api.dto.LancamentoDto;
import com.gabriel.lancamentos.api.dto.LancamentoListDto;
import com.gabriel.lancamentos.api.model.Lancamento;

import lombok.NoArgsConstructor;


@Component
@NoArgsConstructor
public class LancamentoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public  LancamentoDto convertToDto(Lancamento lancamento) {
		return modelMapper.map(lancamento, LancamentoDto.class);
	}

	public   Lancamento convertToEntity(LancamentoDto lancamentoDto) {
		return modelMapper.map(lancamentoDto, Lancamento.class);
	}

	public  LancamentoListDto convertToLancamentoListDto(Lancamento lancamento) {
		return modelMapper.map(lancamento, LancamentoListDto.class);
	}
	
}
