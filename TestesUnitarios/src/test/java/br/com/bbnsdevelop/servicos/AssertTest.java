package br.com.bbnsdevelop.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.bbnsdevelop.entidades.Usuario;


public class AssertTest {

	@Test
	public void test() {
		
		assertTrue(true);
		assertFalse(false);		
		assertEquals(1, 1);
		
		assertEquals(1.1112, 1.11122, 0.0001);
		
		assertEquals(Math.PI, 3.14, 0.01);
		
		int i = 5;
		Integer i2 = 5;
		// Não irá funcionar pois no assertEqual ele não consegue variar autobox de int para Integer
		// Para isto é necessário o int transformar em Wrappers ou o Integer para primitivo
		//assertEquals(i, i2);		
		assertEquals(Integer.valueOf(i), i2);
		assertEquals(i, i2.intValue());
		
		assertTrue("bola".equalsIgnoreCase("Bola"));
		assertTrue("bola".startsWith("bo"));
		
		Usuario u1 = new Usuario("teste 1");
		Usuario u2 = new Usuario("teste 1");
		Usuario u3 = u2;
		
		// essa comparação só é possível devido ao metodo equals sobrescrito na classe Usuario
		assertEquals(u1, u2);
		
		assertSame(u3, u2);
	}
	
	
	@Test
	public void testTath() {

		assertThat(5.0, is(5.0));
		assertThat(5.0, is(equalTo(5.0)));
		assertThat(5.0, is(not(4.0)));
		
	}
}
