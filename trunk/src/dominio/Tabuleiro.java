package dominio;

import java.util.LinkedList;

/**
 * Classe que representa o tabuleiro de um jogo de domino.
 * 
 * @author Gustavo Farias
 */
public class Tabuleiro {

	private LinkedList<Peca> pecasJogadas;

	private static Tabuleiro tabuleiro = null;
	
	public static Tabuleiro getInstance(){
		if(tabuleiro == null)
			return new Tabuleiro();
		
		return tabuleiro;
	}
	
	public Tabuleiro(){
		this.pecasJogadas = new LinkedList<Peca>();
	}
	
	public LinkedList<Peca> getPecasJogadas() {
		return pecasJogadas;
	}
	
	public boolean adicionaPecaEsquerdaTabuleiro(Peca peca){
		if(podeAddEsquerda(peca)){
			pecasJogadas.addFirst(peca);
			return true;
		} else
			return false;

	}

	public boolean adicionaPecaDireitaTabuleiro(Peca peca){
		if(podeAddDireita(peca)){
			pecasJogadas.addLast(peca);
			return true;
		} else
			return false;

	}

	private boolean podeAddEsquerda(Peca peca) {
		return pecasJogadas.getFirst().getLadoEsquerdo() == peca.getLadoDireito();
	}
	
	private boolean podeAddDireita(Peca peca) {
		return pecasJogadas.getLast().getLadoDireito() == peca.getLadoEsquerdo();
	}
	
}
