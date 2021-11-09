package com.gabriel.lancamentos.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gabriel.lancamentos.api.dto.LancamentoListDto;
import com.gabriel.lancamentos.api.model.Lancamento;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Lancamento.class, LancamentoListDto.class)
		.<String>addMapping( src -> src.getCategoria().getNome(), (dest, value) -> dest.setCategoria(value))
		.<String>addMapping( src -> src.getPessoa().getNome(), (dest, value) -> dest.setPessoa(value));
		
		return modelMapper;
	}

}
