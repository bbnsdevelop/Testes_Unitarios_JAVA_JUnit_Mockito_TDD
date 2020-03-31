package br.com.bbnsdevelop.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.bbnsdevelop.calc.CalculadoraTest;
import br.com.bbnsdevelop.servicos.AssertTest;
import br.com.bbnsdevelop.servicos.CalculoValorLocacaoTest;
import br.com.bbnsdevelop.servicos.LocacaoServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	CalculadoraTest.class,
	LocacaoServiceTest.class,
	CalculoValorLocacaoTest.class,
	AssertTest.class
})
public class Execucao {

}
