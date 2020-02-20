package br.com.bbnsdevelop.servicos;

import static br.com.bbnsdevelop.utils.DataUtils.formatDateToDDMMYYY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.com.bbnsdevelop.entidades.Filme;
import br.com.bbnsdevelop.entidades.Locacao;
import br.com.bbnsdevelop.entidades.Usuario;

public class LocacaoServiceTest {

	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
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
	
	@Test(expected = Exception.class)
	public void locacaoSemFilme() throws Exception{
		mockLocacaoFilmeSemEstoque(0);		
	}
	
	
	@Test
	public void locacaoSemFilmeV2() {
		try {
			mockLocacaoFilmeSemEstoque(0);
			fail("Falhou por não lançar exceção");
		} catch (Exception e) {
			assertThat(e.getMessage(), is(equalTo("filme sem estoque")));
		}
		
	}
	
	@Test
	public void locacaoSemFilmeV3() throws Exception{
		// tem que ser verificada antes da execução do cenário, para não falhar
		ex.expect(Exception.class);
		ex.expectMessage("filme sem estoque");

		mockLocacaoFilmeSemEstoque(0);
		
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
	
	private Locacao mockLocacaoFilmeSemEstoque(Integer estoque) throws Exception{
		LocacaoService locacaoService = new LocacaoService();
		
		Usuario usuario = new Usuario("Bruno"); 
		Filme filme = new Filme("A fulga das galinhas", estoque, 5.7);
		
		return locacaoService.alugarFilme(usuario,filme);
		
	}
}
