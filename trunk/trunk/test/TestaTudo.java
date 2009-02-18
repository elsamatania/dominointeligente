import dominio.Jogador_BuscaCegaTest;
import dominio.Jogador_VarMenosRestritivaTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestaTudo extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Todos os testes do dominó.");
		
		suite.addTest(Jogador_BuscaCegaTest.suite());
		suite.addTest(Jogador_VarMenosRestritivaTest.suite());
		
		return suite;
	}
	
}
