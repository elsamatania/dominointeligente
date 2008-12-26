package dominio.jogador;

import dominio.Peca;
import dominio.Tabuleiro;


/**
 * Esta classe implementa o tipo de jogador que utiliza a estrategia de
 * "Busca Cega" para realizar as suas jogadas.
 * 
 * Busca Cega: Percorre as peças, jogando a primeira que se encaixar no Jogo.
 * 
 * @author Gustavo Farias
 *
 */
public class Jogador_BuscaCega extends Jogador {

	public Jogador_BuscaCega(String nome){
		super(nome);
	}

	@Override
	public Peca joga(Tabuleiro tabuleiro) {

		Peca retorno = null;
		
		int i = 0;
		boolean achouJogada = false;
		while(i < getQuantPecas() && !achouJogada){
			
			Peca peca = getPeca(i);
			if(tabuleiro.adicionaPecaEsquerdaTabuleiro(peca)){ // testar: || tabuleiro.adicionaPecaDireitaTabuleiro(peca)){
				achouJogada = true;
				retorno = peca;
				removePeca(i);
			} else if(tabuleiro.adicionaPecaDireitaTabuleiro(peca)){
				achouJogada = true;
				retorno = peca;
				removePeca(i);
			}
			i++;
		}
		
		return retorno;
	}
	
}
