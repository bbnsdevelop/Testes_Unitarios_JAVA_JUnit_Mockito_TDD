package br.com.bbnsdevelop.servicos;

import static br.com.bbnsdevelop.utils.DataUtils.formatDateToDDMMYYY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.com.bbnsdevelop.entidades.Filme;
import br.com.bbnsdevelop.entidades.Locacao;
import br.com.bbnsdevelop.entidades.Usuario;

public class LocacaoServiceTest {

	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void testeLocacao() throws Exception{
		Locacao response = mockLocacao();
		error.checkThat(response , not(equalTo(null)));
		error.checkThat(response.getValor(), not(equalTo(0.0)));
		error.checkThat(formatDateToDDMMYYY(response.getDataLocacao()), not(equalTo(null)));
		error.checkThat("Falso",formatDateToDDMMYYY(response.getDataRetorno()) , not(equalTo(null)));
		error.checkThat(response.getUsuario().getNome() , not(equalTo(null)));
		
		/*
		 	error.checkThat("Falso",formatDateToDDMMYYY(response.getDataRetorno()) , not(equalTo(formatDateToDDMMYYY(response.getDataRetorno()))));
			error.checkThat(response.getUsuario().getNome() , not(equalTo("Bruno")));
		 */
		
	}
	
	@Test
	public void testeObterValor() throws Exception{
		Locacao response = mockLocacao();
		assertTrue(response.getValor() != 0);
	}
	
	@Test
	public void testeObterDataLocacao() throws Exception{
		Locacao response = mockLocacao();
		assertTrue(formatDateToDDMMYYY(response.getDataLocacao()) != null);
	}
	
	
	@Test
	public void testeObterDataRetorno() throws Exception{
		Locacao response = mockLocacao();
		assertTrue("Falso",formatDateToDDMMYYY(response.getDataRetorno()) != null);
	}
	
	@Test
	public void testeObterUsuario() throws Exception{
		Locacao response = mockLocacao();
		assertTrue(response.getUsuario().getNome() != null);
	}
	
	private Locacao mockLocacao() throws Exception{
		LocacaoService locacaoService = new LocacaoService();
		
		Usuario usuario = new Usuario("Bruno"); 
		Filme filme = new Filme("A fulga das galinhas", 1, 5.7);
		
		return locacaoService.alugarFilme(usuario,filme);
		
	}
}
