package com.gabriel.lancamentos.api.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class Endereco {

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;	

}
