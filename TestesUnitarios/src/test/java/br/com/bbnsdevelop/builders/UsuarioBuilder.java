package br.com.bbnsdevelop.builders;

import br.com.bbnsdevelop.entidades.Usuario;

public class UsuarioBuilder {

	
	private Usuario usuario;
	
	private UsuarioBuilder() {
		
	}
	public static UsuarioBuilder newInstance(String user) {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		builder.usuario.setNome(user);
		return builder;
	}
	
	public Usuario get() {
		return this.usuario;
	}
}
