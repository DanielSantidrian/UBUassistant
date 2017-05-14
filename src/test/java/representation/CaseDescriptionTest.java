package representation;

import static org.junit.Assert.*;

import org.junit.Test;

import jcolibri.cbrcore.Attribute;

/**
 * 
 * @author dansa
 *
 */
public class CaseDescriptionTest {
	

	@Test
	public void fieldTest() {
		
		CaseDescription caseDescription = new CaseDescription();
		caseDescription.setId(1);
		caseDescription.setKeyWord1("grado");
		caseDescription.setKeyWord2("ingenieria");
		caseDescription.setKeyWord3("informatica");
		caseDescription.setKeyWord4("online");
		caseDescription.setKeyWord5(null);
		
		assertSame(caseDescription.getId(),1);
		assertEquals("No coincide la palabra clave 1",caseDescription.getKeyWord1(),"grado");
		assertEquals("No coincide la palabra clave 2",caseDescription.getKeyWord2(),"ingenieria");
		assertEquals("No coincide la palabra clave 3",caseDescription.getKeyWord3(),"informatica");
		assertEquals("No coincide la palabra clave 4",caseDescription.getKeyWord4(),"online");
		assertNull("La palabra clave 5 debe ser null",caseDescription.getKeyWord5());

	}
	
	@Test
	public void stringTest() {
		
		CaseDescription caseDescription = new CaseDescription();
		caseDescription.setId(1);
		caseDescription.setKeyWord1("grado");
		caseDescription.setKeyWord2("ingenieria");
		caseDescription.setKeyWord3("informatica");
		caseDescription.setKeyWord4("online");
		caseDescription.setKeyWord5(null);
		
		String expectedOutput="[1 , grado , ingenieria , informatica , online , null]";
		assertEquals(caseDescription.toString(), expectedOutput);

	}
	
	@Test
	public void idTest() {
		
		CaseDescription caseDescription = new CaseDescription();
		assertTrue(caseDescription.getIdAttribute() instanceof Attribute);

	}
	


}
