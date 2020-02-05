package br.com.bbnsdevelop.servicos;

import static br.com.bbnsdevelop.utils.DataUtils.adicionarDias;
import static br.com.bbnsdevelop.utils.DataUtils.formatDateToDDMMYYY;

import java.util.Date;

import br.com.bbnsdevelop.entidades.Filme;
import br.com.bbnsdevelop.entidades.Locacao;
import br.com.bbnsdevelop.entidades.Usuario;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	public static void main(String[] args) {
		LocacaoService locacaoService = new LocacaoService();
		
		Usuario usuario = new Usuario("Bruno"); 
		Filme filme = new Filme("A fulga das galinhas", 1, 5.7);
		
		Locacao response = locacaoService.alugarFilme(usuario,filme);
		System.out.println(response.getValor());
		System.out.println(formatDateToDDMMYYY(response.getDataLocacao()));
		System.out.println(formatDateToDDMMYYY(response.getDataRetorno()));
		System.out.println(response.getUsuario().getNome());
		
		
	}
}