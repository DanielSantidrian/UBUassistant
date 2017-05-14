package representation;

import static org.junit.Assert.*;

import org.junit.Test;
import jcolibri.cbrcore.Attribute;

/**
 * 
 * @author dansa
 *
 */
public class CaseSolutionTest {
	

	@Test
	public void fieldTest() {
		
		CaseSolution caseSolution = new CaseSolution();
		caseSolution.setId(1);
		caseSolution.setAnswer("http://www.ubu.es/grado-en-ingenieria-informatica");
		
		assertSame(caseSolution.getId(),1);
		assertEquals("No coincide la respuesta",caseSolution.getAnswer(),"http://www.ubu.es/grado-en-ingenieria-informatica");
		
	}
	
	@Test
	public void stringTest() {
		
		CaseSolution caseSolution = new CaseSolution();
		caseSolution.setId(1);
		caseSolution.setAnswer("http://www.ubu.es/grado-en-ingenieria-informatica");
		
		String expectedOutput="[1 , http://www.ubu.es/grado-en-ingenieria-informatica]";
		assertEquals(caseSolution.toString(), expectedOutput);

	}
	
	@Test
	public void idTest() {
		
		CaseSolution caseSolution = new CaseSolution();
		assertTrue(caseSolution.getIdAttribute() instanceof Attribute);

	}
	


}
