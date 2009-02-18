package dominio;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import dominio.jogador.Jogador;
import dominio.jogador.Jogador_VarMenosRestritiva;

public class Jogador_VarMenosRestritivaTest extends TestCase {

	private Tabuleiro tabuleiro;
	
	private Jogador jogador;
	
	public static Test suite() {
		return new TestSuite(Jogador_VarMenosRestritivaTest.class);
	}
	
	protected void setUp() throws Exception {
		tabuleiro = new Tabuleiro();
		jogador = new Jogador_VarMenosRestritiva("nome");
	}

	protected void tearDown() throws Exception {
		tabuleiro.reset();
	}
	
	
	public void testAddPecaTabuleiroVazio(){
		
		// cenario
		
		Peca pecaMao = new Peca(2, 4);
		jogador.addPeca(pecaMao);
		
		// teste
		assertEquals(pecaMao, jogador.joga(tabuleiro));
		
	}
	
	public void testAddPecaTabuleiroVazio_2(){
		
		// cenario
		
		Peca peca1 = new Peca(2, 4);
		Peca peca2 = new Peca(2, 5);
		Peca peca3 = new Peca(2, 6);
		Peca peca4 = new Peca(3, 6);
		Peca peca5 = new Peca(3, 1);
		
		jogador.addPeca(peca1);
		jogador.addPeca(peca2);
		jogador.addPeca(peca3);
		jogador.addPeca(peca4);
		jogador.addPeca(peca5);
		
		// teste
		assertEquals(peca3, jogador.joga(tabuleiro));
		
	}
	
	public void testAdicionaMenosRestritiva(){
		
		Peca pecaTab1 = new Peca(3, 6);
		Peca pecaTab2 = new Peca(5, 6);
		
		tabuleiro.adicionaPecaEsquerdaTabuleiro(pecaTab1);
		tabuleiro.adicionaPecaDireitaTabuleiro(pecaTab2);
		
		
		Peca peca1 = new Peca(3, 1);
		Peca peca2 = new Peca(3, 5);
		Peca peca3 = new Peca(5, 1);
		Peca peca4 = new Peca(5, 4);
		
		jogador.addPeca(peca1);
		jogador.addPeca(peca2);
		jogador.addPeca(peca3);
		jogador.addPeca(peca4);
		
		
		// teste
		assertEquals(peca2, jogador.joga(tabuleiro));
		assertEquals(peca2, tabuleiro.getPecaEsquerdaTabuleiro());
		
		assertEquals(peca3, jogador.joga(tabuleiro));
		assertEquals(peca3, tabuleiro.getPecaEsquerdaTabuleiro());
		
		assertEquals(peca1, jogador.joga(tabuleiro));
		assertEquals(peca1, tabuleiro.getPecaEsquerdaTabuleiro());
		
		assertEquals(peca4, jogador.joga(tabuleiro));
		assertEquals(peca4, tabuleiro.getPecaDireitaTabuleiro());
		
	}

	public void testPassaVez(){
		
		// cenario
		Peca pecaTab = new Peca(3, 6);
		tabuleiro.adicionaPecaEsquerdaTabuleiro(pecaTab);
		
		Peca pecaMao = new Peca(2, 4);
		jogador.addPeca(pecaMao);
		
		// teste
		assertNull(jogador.joga(tabuleiro));
		
	}
	
	public void testAdicionaEsquerda_SemInverter(){
		
		// cenario
		Peca pecaTab = new Peca(3, 6);
		tabuleiro.adicionaPecaEsquerdaTabuleiro(pecaTab);
		
		Peca pecaMao = new Peca(2, 3);
		jogador.addPeca(pecaMao);
		
		// teste
		assertEquals(pecaMao, jogador.joga(tabuleiro));
		assertEquals(pecaMao, tabuleiro.getPecasJogadas().getFirst());
		
	}
	
	public void testAdicionaEsquerda_Invertendo(){
		
		// cenario
		Peca pecaTab = new Peca(3, 6);
		tabuleiro.adicionaPecaEsquerdaTabuleiro(pecaTab);
		
		Peca pecaMao = new Peca(3, 2);
		jogador.addPeca(pecaMao);
		
		// teste
		assertEquals(pecaMao, jogador.joga(tabuleiro));
		assertEquals(pecaMao, tabuleiro.getPecasJogadas().getFirst());
		
	}
	
	public void testAdicionaDireita_SemInverter(){
		
		// cenario
		Peca pecaTab = new Peca(3, 6);
		tabuleiro.adicionaPecaEsquerdaTabuleiro(pecaTab);
		
		Peca pecaMao = new Peca(6, 2);
		jogador.addPeca(pecaMao);
		
		// teste
		assertEquals(pecaMao, jogador.joga(tabuleiro));
		assertEquals(pecaMao, tabuleiro.getPecasJogadas().getLast());
		
	}
	
	public void testAdicionaDireita_Invertendo(){
		
		// cenario
		Peca pecaTab = new Peca(3, 6);
		tabuleiro.adicionaPecaEsquerdaTabuleiro(pecaTab);
		
		Peca pecaMao = new Peca(2, 6);
		jogador.addPeca(pecaMao);
		
		// teste
		assertEquals(pecaMao, jogador.joga(tabuleiro));
		assertEquals(pecaMao, tabuleiro.getPecasJogadas().getLast());
		
	}
	
}
