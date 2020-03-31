package br.com.bbnsdevelop.servicos;

import static br.com.bbnsdevelop.matchers.MatchersPropios.caiEm;
import static br.com.bbnsdevelop.matchers.MatchersPropios.caiNaSegunda;
import static br.com.bbnsdevelop.matchers.MatchersPropios.ehHoje;
import static br.com.bbnsdevelop.matchers.MatchersPropios.ehHojeComDiferencaDias;
import static br.com.bbnsdevelop.utils.DataUtils.formatDateToDDMMYYY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.com.bbnsdevelop.builders.FilmeBuilder;
import br.com.bbnsdevelop.builders.UsuarioBuilder;
import br.com.bbnsdevelop.entidades.Filme;
import br.com.bbnsdevelop.entidades.Locacao;
import br.com.bbnsdevelop.entidades.Usuario;
import br.com.bbnsdevelop.exceptions.LocadoraException;
import br.com.bbnsdevelop.utils.DataUtils;

public class LocacaoServiceTest {

	private static LocacaoService locacaoService;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	/*
	 * Execução antes de cada caso de teste
	 * 
	 * @Before public void setup() { this.locacaoService = new LocacaoService(); }
	 * 
	 * @After public void tearDown() { System.out.println("After"); }
	 */

	/*
	 * Com a anotação instancia a classe antes dos medotos serem executados é
	 * preciso colocá-los estaticos de modo que o junit possa utilizá-lo Do contário
	 * irá obter erro de inicialização
	 */
	@BeforeClass
	public static void setup() {
		locacaoService = new LocacaoService();
	}

	@AfterClass
	public static void tearDown() {
		locacaoService = null;
	}
	
	
	@Test
	public void testDevolverFilmeSegundaAoAlugarNoSabado() throws Exception {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		Locacao locacao = mockLocacao();
		Boolean isSegunda = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
		assertTrue(isSegunda);
		assertThat(locacao.getDataRetorno(), caiEm(Calendar.SUNDAY));
		assertThat(locacao.getDataRetorno(), caiNaSegunda());
	}
	
	
	@Test
	public void testNaoDeveDevolverFilmesDomingo() throws Exception {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		Locacao locacao = mockLocacao();
		Boolean isSegunda = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
		assertTrue(isSegunda);		
	}

	@Test
	public void testeLocacao() throws Exception {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SUNDAY));
		Locacao response = mockLocacao();
		error.checkThat(response, not(equalTo(null)));
		error.checkThat(response.getValor(), not(equalTo(0.0)));
		error.checkThat(formatDateToDDMMYYY(response.getDataLocacao()), not(equalTo(null)));
		error.checkThat("Falso", formatDateToDDMMYYY(response.getDataRetorno()), not(equalTo(null)));
		error.checkThat(response.getUsuario().getNome(), not(equalTo(null)));

		error.checkThat(response.getDataLocacao(), ehHoje());
		error.checkThat(response.getDataLocacao(), ehHojeComDiferencaDias(1));
		
		
		/*
		 * error.checkThat("Falso",formatDateToDDMMYYY(response.getDataRetorno()) ,
		 * not(equalTo(formatDateToDDMMYYY(response.getDataRetorno()))));
		 * error.checkThat(response.getUsuario().getNome() , not(equalTo("Bruno")));
		 */

	}

	@Test
	public void descontoAluguelFilmeTresUnidades() throws Exception {
		Filme filme = FilmeBuilder.newInstance().nome("A fulga das galinhas").estoque(1).precoLocacao(5.7).get();
		Filme filme2 = new Filme("A fulga das galinhas", 1, 5.7);
		Filme filme3 = new Filme("A fulga das galinhas", 1, 5.7);
		Double valor =  filme3.getPrecoLocacao() - (filme3.getPrecoLocacao() * 25 / 100);
		Locacao locacao = mockLocacaoFilmeComDesconto(Arrays.asList(filme, filme2, filme3));		
		valor = valor + filme.getPrecoLocacao() + filme2.getPrecoLocacao();
		assertThat(locacao.getValor(), is(equalTo(valor)));

	}
	
	@Test
	public void descontoAluguelFilmeQuatroUnidades() throws Exception {
		Filme filme = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme2 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme3 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme4 = new Filme("A fulga das galinhas", 1, 5.0);
		Double valor3 =  filme3.getPrecoLocacao() - (filme3.getPrecoLocacao() * 25 / 100);
		Double valor =  filme4.getPrecoLocacao() - (filme4.getPrecoLocacao() * 50 / 100);
		
		Locacao locacao = mockLocacaoFilmeComDesconto(Arrays.asList(filme, filme2, filme3, filme4));		
		valor = valor + filme.getPrecoLocacao() + filme2.getPrecoLocacao() + valor3;
		assertThat(locacao.getValor(), is(equalTo(valor)));

	}
	
	@Test
	public void descontoAluguelFilmeCincoUnidades() throws Exception {
		Filme filme = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme2 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme3 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme4 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme5 = new Filme("A fulga das galinhas", 1, 5.0);
		Double valor3 =  filme3.getPrecoLocacao() - (filme3.getPrecoLocacao() * 25 / 100);
		Double valor4 =  filme4.getPrecoLocacao() - (filme4.getPrecoLocacao() * 50 / 100);
		Double valor =  filme5.getPrecoLocacao() - (filme5.getPrecoLocacao() * 75 / 100);
		Locacao locacao = mockLocacaoFilmeComDesconto(Arrays.asList(filme, filme2, filme3, filme4, filme5));		
		valor = valor + filme.getPrecoLocacao() + filme2.getPrecoLocacao() + valor3 + valor4;
		assertThat(locacao.getValor(), is(equalTo(valor)));

	}
	
	@Test
	public void descontoAluguelFilmeSeisUnidades() throws Exception {
		Filme filme = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme2 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme3 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme4 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme5 = new Filme("A fulga das galinhas", 1, 5.0);
		Filme filme6 = new Filme("A fulga das galinhas", 1, 5.0);
		
		Double valor3 =  filme3.getPrecoLocacao() - (filme3.getPrecoLocacao() * 25 / 100);
		Double valor4 =  filme4.getPrecoLocacao() - (filme4.getPrecoLocacao() * 50 / 100);
		Double valor5 =  filme5.getPrecoLocacao() - (filme5.getPrecoLocacao() * 75 / 100);
		
		Double valor =  filme6.getPrecoLocacao() - (filme6.getPrecoLocacao() * 100 / 100);
		Locacao locacao = mockLocacaoFilmeComDesconto(Arrays.asList(filme, filme2, filme3, filme4, filme5, filme6));		
		valor = valor + filme.getPrecoLocacao() + filme2.getPrecoLocacao() + valor3 + valor4 + valor5;
		assertThat(locacao.getValor(), is(equalTo(valor)));

	}

	@Test(expected = LocadoraException.class)
	public void UsuarioNulo() throws Exception {
		mockLocacaoUsuarioNulo();
	}

	@Test(expected = LocadoraException.class)
	public void locacaoFilmeNulo() throws Exception {
		mockLocacaoFilmeNulo();
	}

	@Test(expected = RuntimeException.class)
	public void locacaoSemFilme() throws Exception {
		mockLocacaoFilmeSemEstoque(0);
	}

	@Test
	public void locacaoSemFilmeV2() throws Exception {
		try {
			mockLocacaoFilmeSemEstoque(0);
			fail("Falhou por não lançar exceção");
		} catch (RuntimeException e) {
			assertThat(e.getMessage(),
					is(equalTo("br.com.bbnsdevelop.exceptions.FilmeSemEstoqueException: filme sem estoque")));
		}

	}

	@Test
	public void testUsuarioNuloV3() throws Exception {
		// tem que ser verificada antes da execução do cenário, para não falhar
		ex.expect(LocadoraException.class);
		ex.expectMessage("Usuário não pode ser vazio");
		mockLocacaoUsuarioNulo();

	}

	@Test
	public void testFilmeNuloV3() throws Exception {
		// tem que ser verificada antes da execução do cenário, para não falhar
		ex.expect(LocadoraException.class);
		ex.expectMessage("Filme não pode ser vazio");
		mockLocacaoFilmeNulo();

	}

	@Test
	public void locacaoSemFilmeV3() throws Exception {
		// tem que ser verificada antes da execuao do cenário, para não falhar
		ex.expect(RuntimeException.class);
		ex.expectMessage("filme sem estoque");

		mockLocacaoFilmeSemEstoque(0);

	}

	@Test
	public void testeObterValor() throws Exception {
		Locacao response = mockLocacao();
		assertTrue(response.getValor() != 0);
	}

	@Test
	public void testeObterDataLocacao() throws Exception {
		Locacao response = mockLocacao();
		assertTrue(formatDateToDDMMYYY(response.getDataLocacao()) != null);
	}

	@Test
	public void testeObterDataRetorno() throws Exception {
		Locacao response = mockLocacao();
		assertTrue("Falso", formatDateToDDMMYYY(response.getDataRetorno()) != null);
	}

	@Test
	public void testeObterUsuario() throws Exception {
		Locacao response = mockLocacao();
		assertTrue(response.getUsuario().getNome() != null);
	}

	private Locacao mockLocacao() throws Exception {

		Usuario usuario = UsuarioBuilder.newInstance().nome("usuario 1").get();
		Filme filme = new Filme("A fulga das galinhas", 1, 5.7);
		Filme filme2 = new Filme("A fulga das galinhas", 1, 5.7);
		List<Filme> filmes = Arrays.asList(filme, filme2);

		return locacaoService.alugarFilme(usuario, filmes);

	}

	private Locacao mockLocacaoFilmeComDesconto(List<Filme> filmes) throws Exception {
		Usuario usuario = UsuarioBuilder.newInstance().nome("usuario 1").get();		
		return locacaoService.alugarFilme(usuario, filmes);

	}

	private Locacao mockLocacaoFilmeSemEstoque(Integer estoque) throws Exception {

		Usuario usuario = UsuarioBuilder.newInstance().nome("usuario 1").get();
		Filme filme = new Filme("A fulga das galinhas", estoque, 5.7);
		List<Filme> filmes = Arrays.asList(filme);

		return locacaoService.alugarFilme(usuario, filmes);

	}

	private Locacao mockLocacaoUsuarioNulo() throws Exception {

		Usuario usuario = null;
		Filme filme = new Filme("A fulga das galinhas", 1, 5.7);
		List<Filme> filmes = Arrays.asList(filme);

		return locacaoService.alugarFilme(usuario, filmes);

	}

	private Locacao mockLocacaoFilmeNulo() throws Exception {
		Usuario usuario = UsuarioBuilder.newInstance().nome("usuario 1").get();
		List<Filme> filmes = new ArrayList<Filme>();

		return locacaoService.alugarFilme(usuario, filmes);

	}
}
