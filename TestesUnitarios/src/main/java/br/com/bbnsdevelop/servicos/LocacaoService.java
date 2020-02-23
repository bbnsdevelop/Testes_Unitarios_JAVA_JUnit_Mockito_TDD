package br.com.bbnsdevelop.servicos;

import static br.com.bbnsdevelop.utils.DataUtils.adicionarDias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bbnsdevelop.entidades.Filme;
import br.com.bbnsdevelop.entidades.Locacao;
import br.com.bbnsdevelop.entidades.Usuario;
import br.com.bbnsdevelop.exceptions.FilmeSemEstoqueException;
import br.com.bbnsdevelop.exceptions.LocadoraException;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws LocadoraException, FilmeSemEstoqueException {
		
		if(usuario == null) {
			throw new LocadoraException("Usu�rio n�o pode ser vazio");
		}
		
		if(filmes == null || filmes.isEmpty() || filmes.size() == 0) {
			throw new LocadoraException("Filme n�o pode ser vazio");
		}
		
		if(filmes.isEmpty()) {
			throw new LocadoraException("Filme n�o podem ser vazios");
		}
		List<Filme> FilmeLocacoes = new ArrayList<>();
		
		filmes.forEach(filme -> {
			try {
				if(filme.getEstoque() == 0) {
					throw new FilmeSemEstoqueException("filme sem estoque");
				}
				FilmeLocacoes.add(filme);
			} catch (FilmeSemEstoqueException e) {
				throw new RuntimeException(e);
				
			}
		});
		Locacao locacao = new Locacao();			
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());				
		Date dataEntrega = new Date();
		//Entrega no dia seguinte
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		locacao.setFilme(FilmeLocacoes);
		locacao.setValor(filmes.stream().mapToDouble(f -> f.getPrecoLocacao()).sum()); 

		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		return locacao;
		
	}

	
}