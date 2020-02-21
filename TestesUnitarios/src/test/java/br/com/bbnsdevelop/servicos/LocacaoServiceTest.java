package br.com.bbnsdevelop.servicos;

import static br.com.bbnsdevelop.utils.DataUtils.formatDateToDDMMYYY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.com.bbnsdevelop.entidades.Filme;
import br.com.bbnsdevelop.entidades.Locacao;
import br.com.bbnsdevelop.entidades.Usuario;
import br.com.bbnsdevelop.exceptions.FilmeSemEstoqueException;
import br.com.bbnsdevelop.exceptions.LocadoraException;

public class LocacaoServiceTest {

	
	private static LocacaoService locacaoService;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	/*
	 * Execução antes de cada caso de teste
	 * 
	@Before
	public void setup() {
		this.locacaoService = new LocacaoService();
	}
	@After
	public void tearDown() {
		System.out.println("After");
	}*/
	
	
	/*Com a anotação instancia a classe antes dos medotos serem executados é preciso colocá-los estaticos de modo que o junit possa utilizálo
	 * Do contário irá obter erro de inicialização
	 * */
	@BeforeClass
	public static void setup() {
		locacaoService = new LocacaoService();
	}
	@AfterClass
	public static void tearDown() {
		System.out.println("After");		
		locacaoService = null;
	}
	
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
	
	
	
	@Test(expected = LocadoraException.class)
	public void UsuarioNulo() throws Exception{
		mockLocacaoUsuarioNulo();		
	}
	
	@Test(expected = LocadoraException.class)
	public void locacaoFilmeNulo() throws Exception{
		mockLocacaoFilmeNulo();		
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
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
	public void testUsuarioNuloV3() throws Exception{
		// tem que ser verificada antes da execução do cenário, para não falhar
		ex.expect(LocadoraException.class);
		ex.expectMessage("Usuário não pode ser vazio");
		mockLocacaoUsuarioNulo();
		
	}
	
	@Test
	public void testFilmeNuloV3() throws Exception{
		// tem que ser verificada antes da execução do cenário, para não falhar
		ex.expect(LocadoraException.class);
		ex.expectMessage("Filme não pode ser vazio");
		mockLocacaoFilmeNulo();
		
	}
	
	@Test
	public void locacaoSemFilmeV3() throws Exception{
		// tem que ser verificada antes da execução do cenário, para não falhar
		ex.expect(FilmeSemEstoqueException.class);
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
		
		Usuario usuario = new Usuario("Bruno"); 
		Filme filme = new Filme("A fulga das galinhas", 1, 5.7);
		
		return locacaoService.alugarFilme(usuario,filme);
		
	}
	
	private Locacao mockLocacaoFilmeSemEstoque(Integer estoque) throws Exception{
		
		Usuario usuario = new Usuario("Bruno"); 
		Filme filme = new Filme("A fulga das galinhas", estoque, 5.7);
		
		return locacaoService.alugarFilme(usuario,filme);
		
	}
	
	private Locacao mockLocacaoUsuarioNulo() throws Exception{
		
		Usuario usuario = null; 
		Filme filme = new Filme("A fulga das galinhas", 1, 5.7);
		
		return locacaoService.alugarFilme(usuario,filme);
		
	}
	
	private Locacao mockLocacaoFilmeNulo() throws Exception{
		
		Usuario usuario = new Usuario("Bruno"); 
		Filme filme = null;
		
		return locacaoService.alugarFilme(usuario,filme);
		
	}
}
