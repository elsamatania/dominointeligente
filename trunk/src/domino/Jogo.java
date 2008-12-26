package domino;

import java.util.Scanner;

import dominio.jogador.Jogador;
import dominio.jogador.JogadorFactory;
import dominio.jogador.Jogador_BuscaCega;

/**
 * Classe prinipal do Jogo de domino. Eh a responsavel pela
 * criacao do ambiente, inicializacao e controle das jogadas
 * do Jogo.
 * 
 * @author Gustavo Farias
 *
 */
public class Jogo {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Este jogo de domino será jogado por 2 jogadores virtuais. Cada jogador\n" + 
				"irá utilizar uma estratégia escolhida antes de o jogo começar. Vamos agora à\n" +
				"criação de cada jogador e à escolha de suas estratégias.");
		
		System.out.println("Digite o nome do Jogador 1: ");
		String nomeJogador1 = sc.nextLine();
		System.out.println("Escolha a estrategia de: " + nomeJogador1);
		exibeMenuEscolhaEstrategia();
		int estrategiaJogador1 = sc.nextInt();
		Jogador jogador1 = JogadorFactory.getInstance().getJogador(estrategiaJogador1, nomeJogador1);
		
		System.out.println("Digite o nome do Jogador 2: ");
		String nomeJogador2 = sc.nextLine();
		System.out.println("Escolha a estrategia de: " + nomeJogador2);
		exibeMenuEscolhaEstrategia();
		int estrategiaJogador2 = sc.nextInt();
		Jogador jogador2 = JogadorFactory.getInstance().getJogador(estrategiaJogador2, nomeJogador2);
		
		inicializacaoJogo(jogador1.getNome(), jogador2.getNome());
		
		boolean jogoAcabou = false;
		while(!jogoAcabou){
			
			impimeTabuleiro();
			imprimeMaosJogadores();
			aguardaProximaJogada();
			
		}
		
	}
	
	// =====================================================================
	// ========== Metodos que imprimem informacoes sobre o jogo. ===========
	// =====================================================================

	private static void inicializacaoJogo(String jogador1, String jogador2) throws InterruptedException {

		System.out.println("Misturando as peças...");
		Thread.sleep(1000);
		
		System.out.println("Distribuindo entre os jogadores...");
		Thread.sleep(1000);
		
		System.out.println("Peças de " + jogador1 + "\n");
		System.out.println("Peças de " + jogador2 + "\n\n");
		
		System.out.println("Tecle <ENTER> para que uma nova jogada seja realizada. " +
				"O jogador " + jogador1 + " começará o jogo.");
		
	}

	private static void exibeMenuEscolhaEstrategia() {
		
		System.out.println("1 - Busca Cega: o jogador irá percorrer suas peças da esquerda para a direita \n" +
				"e jogar a primeira que se encaixar no tabuleiro.");
		
		System.out.println("2 - ");
		
	}

	private static void aguardaProximaJogada() {
		// TODO Auto-generated method stub
		
	}

	private static void imprimeMaosJogadores() {
		// TODO Auto-generated method stub
		
	}

	private static void impimeTabuleiro() {
		// TODO Auto-generated method stub
		
	}
	

}

