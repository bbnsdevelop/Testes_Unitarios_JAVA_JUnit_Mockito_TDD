package br.com.bbnsdevelop.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.bbnsdevelop.builders.UsuarioBuilder;
import br.com.bbnsdevelop.entidades.Filme;
import br.com.bbnsdevelop.entidades.Locacao;
import br.com.bbnsdevelop.entidades.Usuario;


/*
 * TEST DATA DRIVEN 
 */
@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

	private static LocacaoService locacaoService;

	@Parameter
	public List<Filme> filmes;

	@Parameter(value = 1)
	public Double valorFilmes;
	
	@Parameter(value = 2)
	public String cenario;

	@BeforeClass
	public static void setup() {
		locacaoService = new LocacaoService();
	}

	@Test
	public void calcularValorLocacao() throws Exception {

		Locacao locacao = mockLocacaoFilmeComDesconto();
		assertThat(locacao.getValor(), is(equalTo(valorFilmes)));

	}

	private Locacao mockLocacaoFilmeComDesconto() throws Exception {
		Usuario usuario = UsuarioBuilder.newInstance().nome("usuario 1").get();
		return locacaoService.alugarFilme(usuario, filmes);

	}

	@Parameters(name = "{2}")
	public static Collection<Object[]> getParametros() {
		return Arrays.asList(new Object[][] {
				{ Arrays.asList(new Filme("A fulga das galinhas 1", 1, 4.0),
						new Filme("A fulga das galinhas 2", 1, 4.0), new Filme("A fulga das galinhas 3", 1, 4.0)), 11.0, "3 Filmes: 25%" 
				},
				{ Arrays.asList(new Filme("A fulga das galinhas 1", 1, 4.0),
						new Filme("A fulga das galinhas 2", 1, 4.0), new Filme("A fulga das galinhas 3", 1, 4.0),
						new Filme("A fulga das galinhas 4", 1, 4.0)), 13.0, "4 Filmes: 50%" 
				},
				{ Arrays.asList(new Filme("A fulga das galinhas 1", 1, 4.0),
						new Filme("A fulga das galinhas 2", 1, 4.0), new Filme("A fulga das galinhas 3", 1, 4.0),
						new Filme("A fulga das galinhas 4", 1, 4.0), new Filme("A fulga das galinhas 5", 1, 4.0)), 14.0, "5 Filmes: 75%" 
				},
				{ Arrays.asList(new Filme("A fulga das galinhas 1", 1, 4.0),
						new Filme("A fulga das galinhas 2", 1, 4.0), new Filme("A fulga das galinhas 3", 1, 4.0),
						new Filme("A fulga das galinhas 4", 1, 4.0), new Filme("A fulga das galinhas 5", 1, 4.0),
						new Filme("A fulga das galinhas 6", 1, 4.0)), 14.0, "6 Filmes: 100%"
				} 
		});
	}

}
