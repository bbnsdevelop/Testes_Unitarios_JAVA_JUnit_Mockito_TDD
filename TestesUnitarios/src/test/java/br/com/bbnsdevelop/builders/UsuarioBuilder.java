package br.com.bbnsdevelop.builders;

import br.com.bbnsdevelop.entidades.Usuario;

public class UsuarioBuilder {

	
	private Usuario usuario;
	
	private UsuarioBuilder() {
		
	}
	public static UsuarioBuilder newInstance() {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		return builder;
	}
	
	public UsuarioBuilder nome(String nome) {
		this.usuario.setNome(nome);
		return this;
	}
	
	public Usuario get() {
		return this.usuario;
	}
}
