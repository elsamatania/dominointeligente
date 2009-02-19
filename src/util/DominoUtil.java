package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dominio.Peca;
import dominio.jogador.Jogador;

/**
 * Classe que realiza operacoes auxiliares ao jogo de domino.
 * 
 * @author Gustavo Farias
 *
 */
public class DominoUtil {

	public static void misturaPecas(Set<Peca> pecas){
		List<Peca> aux = new ArrayList<Peca>(pecas);
		Collections.shuffle(aux);
		pecas = new HashSet<Peca>(aux);
	}
	
	
	/**
	 * Distribui 14 pecas para cada jogador.
	 * @param jogador1
	 * @param jogador2
	 * @param todasAsPecas
	 */
	public static void distribuiPecas(Jogador jogador1, Jogador jogador2, Set<Peca> todasAsPecas) {

		Iterator<Peca> it = todasAsPecas.iterator();
		
		int i = 0;
		while(i < todasAsPecas.size()/2){ // com 28 pecas, sao 14 iteracoes
			jogador1.addPeca(it.next());
			jogador2.addPeca(it.next());
			i++;
		}
		
	}
	
}
