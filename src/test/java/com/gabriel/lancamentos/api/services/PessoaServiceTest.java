package com.gabriel.lancamentos.api.services;



import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gabriel.lancamentos.api.model.Pessoa;
import com.gabriel.lancamentos.api.repository.PessoaRepository;
import com.gabriel.lancamentos.api.service.PessoaService;
import com.gabriel.lancamentos.api.utils.PessoaCreator;

@ExtendWith(SpringExtension.class)
class PessoaServiceTes {
    
	@InjectMocks
    private PessoaService pessoaService;

	@Mock
    private PessoaRepository pessoaRepository;

    @BeforeEach
    void setUp(){
    	
    	Pessoa pessoaValidaSalva = PessoaCreator.pessoaValidaSalva();
         
     
    	BDDMockito.when(pessoaRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(pessoaValidaSalva));

    	BDDMockito.when(pessoaRepository.save(ArgumentMatchers.any()))
                .thenReturn(pessoaValidaSalva);
    	    	
    }
    
    @Test
    @DisplayName("Cria pessoa com atribuitos passados por parametro")
    void cria_RetornaPessoaCriada_CasoSucesso(){

       Pessoa pessoaMock = PessoaCreator.pessoaValidaParaSerSalva();
       Pessoa pessoa = this.pessoaService.salvar(pessoaMock);
       
       Assertions.assertNotNull(pessoa);
       Assertions.assertTrue(pessoa instanceof Pessoa);
       Assertions.assertEquals(pessoa.getNome(), pessoaMock.getNome());             
 
     }

    
    @Test
    @DisplayName("Atualiza dados pessoa passando código e objeto")
    void atualiza_RetornaPessoaCriada_CasoSucesso(){

       Pessoa pessoaMock = PessoaCreator.pessoaValidaSalva();
       Pessoa pessoa = this.pessoaService.atualizar(pessoaMock.getCodigo(), pessoaMock);
       
       Assertions.assertNotNull(pessoa);
       Assertions.assertTrue(pessoa instanceof Pessoa);
       Assertions.assertEquals(pessoa.getCodigo(), pessoaMock.getCodigo());             
 
     }
    
     
    @Test
    @DisplayName("Dado ativo igual true, então atualiza a propiedade ativo para true")
    void atualizaAtivo_CasoSucesso(){
    	
    	BDDMockito.when(pessoaRepository.save(ArgumentMatchers.any()))
        .thenReturn(PessoaCreator.pessoaAtivoTrueSalva());
  
    	Pessoa pessoa = PessoaCreator.pessoaAtivoFalseSalva();
        Pessoa pessoaAtualizada = this.pessoaService.atualizarPropriedadeAtivo(pessoa.getCodigo(), true);
       
       Assertions.assertNotNull(pessoaAtualizada);
       Assertions.assertTrue(pessoaAtualizada instanceof Pessoa);
       Assertions.assertEquals(pessoaAtualizada.getCodigo(), pessoa.getCodigo());
       Assertions.assertEquals(pessoaAtualizada.getAtivo(), pessoa.getAtivo());
       Assertions.assertTrue(pessoaAtualizada.getAtivo());
 
     }
    
    
    @Test
    @DisplayName("Caso pessoa igual a null, então lanca exceção NotFoundException.")
    void atualizaAtivo_CasoFalha(){
    	
    	BDDMockito.when(pessoaRepository.findById(ArgumentMatchers.anyLong()))
    			.thenReturn(Optional.empty());
    	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> 
    		pessoaService.atualizarPropriedadeAtivo(null, null)
    	);
 
     }

    




}