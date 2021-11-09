package com.gabriel.lancamentos.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.lancamentos.api.dto.LancamentoDto;
import com.gabriel.lancamentos.api.dto.LancamentoListDto;
import com.gabriel.lancamentos.api.event.RecursoCriadoEvent;
import com.gabriel.lancamentos.api.mapper.LancamentoMapper;
import com.gabriel.lancamentos.api.model.Lancamento;
import com.gabriel.lancamentos.api.model.TipoLancamento;
import com.gabriel.lancamentos.api.repository.LancamentoRepository;
import com.gabriel.lancamentos.api.repository.filter.LancamentoFilter;
import com.gabriel.lancamentos.api.repository.projections.LancamentoView;
import com.gabriel.lancamentos.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	LancamentoMapper lancamentoMapper;

	@GetMapping("/filtrar")
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}

	@GetMapping
	public List<LancamentoDto> listar() {
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		return lancamentos.stream().map(lancamentoMapper::convertToDto).collect(Collectors.toList());
	}

	@GetMapping(value = "/{tipoLancamento}")
	public List<LancamentoView> getValorByTipo(@PathVariable TipoLancamento tipoLancamento) {
		return lancamentoRepository.getValorByTipo(tipoLancamento);
	}

	@GetMapping("/listarResumo")
	public List<LancamentoListDto> listarResumo() {
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		return lancamentos.stream().map(lancamentoMapper::convertToLancamentoListDto).collect(Collectors.toList());
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<LancamentoDto> criar(@Valid @RequestBody LancamentoDto lancamentoDto,
			HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamentoMapper.convertToEntity(lancamentoDto));
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoMapper.convertToDto(lancamentoSalvo));
	}
	
}
