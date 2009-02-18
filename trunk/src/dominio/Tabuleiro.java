package dominio;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Classe que representa o tabuleiro de um Jogo de domino.
 * 
 * @author Gustavo Farias
 */
public class Tabuleiro {

	private Set<Peca> todasAsPecas;
	
	private LinkedList<Peca> pecasJogadas;

	private static Tabuleiro tabuleiro = null;
	
	public static Tabuleiro getInstance(){
		if(tabuleiro == null)
			return new Tabuleiro();
		
		return tabuleiro;
	}
	
	public Tabuleiro(){
		this.pecasJogadas = new LinkedList<Peca>();
		todasAsPecas = new HashSet<Peca>();
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
		
		if(estaVazio() || pecasJogadas.getFirst().getLadoEsquerdo() == peca.getLadoDireito())
			return true;
		
		if(pecasJogadas.getFirst().getLadoEsquerdo() == peca.getLadoEsquerdo()){
			invertePeca(peca);
			return true;
		}
		
		return false;
	}
	
	private boolean podeAddDireita(Peca peca) {
		
		if(estaVazio() || pecasJogadas.getLast().getLadoDireito() == peca.getLadoEsquerdo())
			return true;
		
		if(pecasJogadas.getLast().getLadoDireito() == peca.getLadoDireito()){
			invertePeca(peca);
			return true;
		}
		
		return false;
	}

	public void reset() {
		this.pecasJogadas.clear();
	}
	
	private void invertePeca(Peca peca) {
		int aux = peca.getLadoEsquerdo();
		peca.setLadoEsquerdo(peca.getLadoDireito());
		peca.setLadoDireito(aux);
	}
	
	public String toString(){
		String out = "";
		
		for (int i = 0; i < pecasJogadas.size(); i++) {
			out += pecasJogadas.get(i).toString() + " ";
		}
		
		return out;
	}
	
	public Peca getPecaEsquerdaTabuleiro(){
		return pecasJogadas.getFirst();
	}
	
	public Peca getPecaDireitaTabuleiro(){
		return pecasJogadas.getLast();
	}
	
	public Set<Peca> getTodasAsPecas() {
		return todasAsPecas;
	}

	public void instanciaTodasAsPecas() {
		
		// pecas com ZERO
		for (int i = 0; i < 7; i++) {
			todasAsPecas.add(new Peca(0, i));
		}
		
		// pecas com UM
		for (int i = 1; i < 7; i++) {
			todasAsPecas.add(new Peca(1, i));
		}
		
		// pecas com DOIS
		for (int i = 2; i < 7; i++) {
			todasAsPecas.add(new Peca(2, i));
		}
		
		// pecas com TRES
		for (int i = 3; i < 7; i++) {
			todasAsPecas.add(new Peca(3, i));
		}
		
		// pecas com QUATRO
		for (int i = 4; i < 7; i++) {
			todasAsPecas.add(new Peca(4, i));
		}
		
		// pecas com CINCO
		for (int i = 5; i < 7; i++) {
			todasAsPecas.add(new Peca(5, i));
		}
		
		todasAsPecas.add(new Peca(6, 6));
		
	}

	public boolean estaVazio() {
		return pecasJogadas.size() == 0;
	}

}
