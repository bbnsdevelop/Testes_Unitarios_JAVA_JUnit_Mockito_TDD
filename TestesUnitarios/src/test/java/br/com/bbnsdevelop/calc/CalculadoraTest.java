package br.com.bbnsdevelop.calc;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculadoraTest {

	private static Calculadora calc;
	
	@BeforeClass
	public static void setup() {
		calc = new Calculadora();
	}
	
	@Test
	public void testSomarDoisValores() {
		int a = 3;
		int b = 5;		
		int resultado = calc.soma(a,b);
		assertEquals(8, resultado);
	}
	
	@Test
	public void testSubtrairDoisValores() {
		int a = 6;
		int b = 3;
		int resultado = calc.subtrair(a, b);
		assertEquals(3, resultado);
	}
	
	@Test
	public void testMultiplicaDoisValores() {
		int a = 6;
		int b = 3;
		int resultado = calc.multiplica(a, b);
		assertEquals(18, resultado);
	}
	
	@Test
	public void testDivideDoisValores() {
		int a = 6;
		int b = 3;
		int resultado = calc.divide(a, b);
		assertEquals(2, resultado);
	}
	
	@Test(expected = ArithmeticException.class)
	public void testLnacaExecaoSeOQuocienteDaDivisaoForZero() {
		int a = 6;
		int b = 0;
		int resultado = calc.divide(a, b);
		assertEquals(2, resultado);
		
	}
	
}
