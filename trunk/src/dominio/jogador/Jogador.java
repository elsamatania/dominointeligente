package dominio.jogador;

import java.util.LinkedList;

import dominio.Peca;
import dominio.Tabuleiro;

/**
 * Classe que representa um jogador de domino.
 * @author Gustavo Farias
 *
 */
public abstract class Jogador {

	private LinkedList<Peca> pecas;
	
	private String nome;
	
	public Jogador(String nome){
		this.nome = nome;
		pecas = new LinkedList<Peca>();
	}

	/**
	 * Adiciona uma peca para a mao do jogador.
	 * @param peca
	 */
	public void addPeca(Peca peca){
		this.pecas.addLast(peca);
	}
	
	/**
	 * Remove da mao do jogador a peca especificada pela posicao recebida.  
	 * @param posicao
	 * @return a peca retirada ou null, caso a posicao seja invalida.
	 */
	public Peca removePeca(int posicao){
		
		try {
			return pecas.get(posicao);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * Retorna o nome do jogador.
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo que define a forma de jogar do jogador. Cada
	 * tipo de jogador que extender esta classe devera sobrescrever 
	 * este metodo e implementar a sua propria estrategia de Jogo.
	 * @param tabuleiro
	 * @return A peca jogada, ou null, caso o jogador passe a vez, ou seja,
	 * nenhuma de suas pecas se encaixe no tabuleiro. 
	 */
	public abstract Peca joga(Tabuleiro tabuleiro);

	
	public int getQuantPecas() {
		return pecas.size();
	}
	
	/**
	 * Retorna uma Peca, sem retira-la da mao do jogador.
	 * @param posicao
	 * @return
	 */
	public Peca getPeca(int posicao) {
		return pecas.get(posicao);
	}
	
}
