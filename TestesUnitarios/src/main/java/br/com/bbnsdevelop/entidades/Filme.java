package br.com.bbnsdevelop.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Filme {

	private String nome;
	private Integer estoque;
	private Double precoLocacao;  
	
	
}