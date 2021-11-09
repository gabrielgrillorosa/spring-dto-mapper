package com.gabriel.lancamentos.api;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.lancamentos.api.dto.LancamentoDto;
import com.gabriel.lancamentos.api.model.Lancamento;
import com.gabriel.lancamentos.api.utils.LancamentoCreator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class LacamentoDtoUnitTest {
    private ModelMapper modelMapper = new ModelMapper();
    

    @Test
    public void quando_ConverterLancamentoEntitdadeParaLcamentoDto_EntaoSucesso() throws JsonProcessingException {
        Lancamento lancamento = LancamentoCreator.criarLancamentoValidoSalvo();
        LancamentoDto lancamentoDto = modelMapper.map(lancamento, LancamentoDto.class);
        ObjectMapper mapper = new ObjectMapper();
        log.info(mapper.writeValueAsString(lancamentoDto));
        System.out.print(mapper.writeValueAsString(lancamentoDto));
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        LancamentoDto lancamentoDto = LancamentoCreator.criarLancamentoParaSerSalvo();
        Lancamento lancamento = modelMapper.map(lancamentoDto, Lancamento.class);
        log.info(lancamento.toString());
    }


}

