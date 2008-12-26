package dominio;

import dominio.jogador.Jogador;
import dominio.jogador.Jogador_BuscaCega;
import junit.framework.TestCase;

public class Jogador_BuscaCegaTest extends TestCase {

	private Tabuleiro tabuleiro;
	
	private Jogador jogador;
	
	protected void setUp() throws Exception {
		tabuleiro = new Tabuleiro();
		jogador = new Jogador_BuscaCega("nome");
	}

	protected void tearDown() throws Exception {
		tabuleiro.reset();
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
	
	public void testAdicionaPrimeraPecaQueAparecer(){
		
		// cenario
		Peca pecaTab = new Peca(3, 6);
		tabuleiro.adicionaPecaEsquerdaTabuleiro(pecaTab);
		
		Peca pecaMao1 = new Peca(2, 4);
		Peca pecaMao2 = new Peca(1, 6);
		Peca pecaMao3 = new Peca(3, 5);
		Peca pecaMao4 = new Peca(6, 6);
		jogador.addPeca(pecaMao1);
		jogador.addPeca(pecaMao2);
		jogador.addPeca(pecaMao3);
		jogador.addPeca(pecaMao4);
		
		// teste
		assertEquals(pecaMao2, jogador.joga(tabuleiro));
		assertEquals(pecaMao2, tabuleiro.getPecasJogadas().getLast());
		
	}
	
	public void testAdicionaPrimeraPecaQueAparecer_2(){
		
		// cenario
		Peca pecaTab = new Peca(3, 6);
		tabuleiro.adicionaPecaEsquerdaTabuleiro(pecaTab);
		
		Peca pecaMao1 = new Peca(2, 4);
		Peca pecaMao2 = new Peca(1, 6);
		Peca pecaMao3 = new Peca(3, 5);
		Peca pecaMao4 = new Peca(6, 6);
		jogador.addPeca(pecaMao1);
		jogador.addPeca(pecaMao3);
		jogador.addPeca(pecaMao2);
		jogador.addPeca(pecaMao4);
		
		// teste
		assertEquals(pecaMao3, jogador.joga(tabuleiro));
		assertEquals(pecaMao3, tabuleiro.getPecasJogadas().getFirst());
		
	}
	
}
