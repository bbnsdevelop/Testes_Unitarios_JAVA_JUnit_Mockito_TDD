package br.com.bbnsdevelop.servicos;

import static br.com.bbnsdevelop.utils.DataUtils.formatDateToDDMMYYY;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.bbnsdevelop.entidades.Filme;
import br.com.bbnsdevelop.entidades.Locacao;
import br.com.bbnsdevelop.entidades.Usuario;
import br.com.bbnsdevelop.servicos.LocacaoService;

public class LocacaoServiceTest {

	
	
	@Test
	public void testeAlugarFilme() {
		LocacaoService locacaoService = new LocacaoService();
		
		Usuario usuario = new Usuario("Bruno"); 
		Filme filme = new Filme("A fulga das galinhas", 1, 5.7);
		
		Locacao response = locacaoService.alugarFilme(usuario,filme);
		assertTrue(response.getValor() != 0);
		assertTrue(formatDateToDDMMYYY(response.getDataLocacao()) != null);
		assertTrue("Falso",formatDateToDDMMYYY(response.getDataRetorno()) != null);
		assertTrue(response.getUsuario().getNome() != null);
		
		
	}
}
