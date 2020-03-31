package br.com.bbnsdevelop.builders;

import br.com.bbnsdevelop.entidades.Filme;

public class FilmeBuilder {
	
	private Filme filme;
	
	private FilmeBuilder() {
		
	}
	
	public static FilmeBuilder newInstance(String nome, Integer estoque, Double precoLocacao) {
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filme();
		builder.filme.setNome(nome);
		builder.filme.setEstoque(estoque);
		builder.filme.setPrecoLocacao(precoLocacao);
		return builder;		
	}
	
	public Filme get() {
		return this.filme;
	}

}
