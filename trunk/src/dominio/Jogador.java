package dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um jogador de domino.
 * @author Gustavo Farias
 *
 */
public abstract class Jogador {

	private List<Peca> pecas;
	
	private String nome;
	
	public Jogador(String nome){
		this.nome = nome;
		pecas = new ArrayList<Peca>();
	}

	/**
	 * Adiciona uma peca para a mao do jogador.
	 * @param peca
	 */
	public void addPeca(Peca peca){
		this.pecas.add(peca);
	}
	
	/**
	 * Retira da mao do jogador a peca especificada pela posicao recebida.  
	 * @param posicao
	 * @return a peca retirada ou null, caso a posicao seja invalida.
	 */
	public Peca retiraPeca(int posicao){
		
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
	 * este metodo e implementar a sua propria estrategia de jogo.
	 * @param tabuleiro
	 * @return o codigo de retorno associoado a jogada executada. A relacao
	 * 			dos codigos de retornos existentes esta na interface ConstantesCodigoRetorno. 
	 */
	public abstract String joga(Tabuleiro tabuleiro);
	
}
