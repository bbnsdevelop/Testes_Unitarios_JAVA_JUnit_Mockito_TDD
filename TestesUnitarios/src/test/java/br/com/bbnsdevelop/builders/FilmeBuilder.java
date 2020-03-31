package br.com.bbnsdevelop.builders;

import br.com.bbnsdevelop.entidades.Filme;

public class FilmeBuilder {
	
	private Filme filme;
	
	private FilmeBuilder() {
		
	}
	
	public static FilmeBuilder newInstance() {
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filme();
		return builder;		
	}
	
	public FilmeBuilder nome(String nome) {
		filme.setNome(nome);
		return this;
	}
	
	public FilmeBuilder estoque(Integer estoque) {
		filme.setEstoque(estoque);
		return this;
	}
	public FilmeBuilder precoLocacao(Double precoLocacao) {
		filme.setPrecoLocacao(precoLocacao);
		return this;
	}
	
	public Filme get() {
		return this.filme;
	}

}
