package domino;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import util.DominoUtil;

import dominio.Peca;
import dominio.Tabuleiro;
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
		System.out.println("Escolha a estrategia de " + nomeJogador1 + " :");
		exibeMenuEscolhaEstrategia();
		int estrategiaJogador1 = sc.nextInt();
		Jogador jogador1 = JogadorFactory.getInstance().getJogador(estrategiaJogador1, nomeJogador1);
		
		System.out.println("Digite o nome do Jogador 2: ");
		String nomeJogador2 = sc.nextLine();
		System.out.println("Escolha a estrategia de " + nomeJogador2 + " :");
		exibeMenuEscolhaEstrategia();
		int estrategiaJogador2 = sc.nextInt();
		Jogador jogador2 = JogadorFactory.getInstance().getJogador(estrategiaJogador2, nomeJogador2);
		
		Tabuleiro tabuleiro = new Tabuleiro();
		inicializacaoJogo(jogador1, jogador2, tabuleiro);
		
		boolean jogoAcabou = false;
		
		// a cada iteracao no loop ocorrem 2 jogadas, uma de cada jogador.
		while(!jogoAcabou){
			
			iniciaRodada();
			
			System.out.println("Jogador " + jogador1.getNome() + " joga.\n");
			aguardaProximaJogada(sc);
			jogador1.joga(tabuleiro);
			impimeTabuleiro(tabuleiro);
			imprimeMaosJogadores(jogador1, jogador2);
			
			// jogador verifica se venceu...
			
			System.out.println("Jogador " + jogador2.getNome() + " joga.\n");
			aguardaProximaJogada(sc);
			jogador2.joga(tabuleiro);
			impimeTabuleiro(tabuleiro);
			imprimeMaosJogadores(jogador1, jogador2);
			
			// jogador verifica se venceu...
			
			encerraRodada();
			
			// jogo verifica se o jogo esta congelado e, no caso positivo, conta os pontos. 
			
		}
		
	}
	
	// =====================================================================
	// ========== Metodos que imprimem informacoes sobre o jogo. ===========
	// =====================================================================

	private static void inicializacaoJogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) throws InterruptedException {

		System.out.println("Criando e misturando as peças...");
		tabuleiro.instanciaTodasAsPecas();
		Set<Peca> todasAsPecas = tabuleiro.getTodasAsPecas();
		DominoUtil.misturaPecas(todasAsPecas);
		Thread.sleep(1000);
		
		System.out.println("Distribuindo entre os jogadores...\n");
		distribuiPecas(jogador1, jogador2, todasAsPecas);
		Thread.sleep(1000);
		
		System.out.println("Peças de " + jogador1.getNome() + ": " + jogador1.getPecasComoString() + "\n");
		
		System.out.println("Peças de " + jogador2.getNome() + ": " + jogador2.getPecasComoString() + "\n");
		
		System.out.println("Tecle <ENTER> para que cada nova jogada seja realizada. " +
				"O jogador " + jogador1.getNome() + " começará o jogo.\n");
		
	}

	/**
	 * Mistura as pecas e distribui 14 para cada jogador.
	 * @param jogador1
	 * @param jogador2
	 * @param todasAsPecas
	 */
	private static void distribuiPecas(Jogador jogador1, Jogador jogador2, Set<Peca> todasAsPecas) {

		Iterator<Peca> it = todasAsPecas.iterator();
		
		int i = 0;
		while(i < todasAsPecas.size()/2){ // com 28 pecas, sao 14 iteracoes
			jogador1.addPeca(it.next());
			jogador2.addPeca(it.next());
			i++;
		}
		
	}

	private static void exibeMenuEscolhaEstrategia() {
		
		System.out.println("1 - Busca Cega: o jogador irá percorrer suas peças da esquerda para a direita \n" +
				"e jogar a primeira que se encaixar no tabuleiro.");
		
		System.out.println("2 - ");
		
	}

	private static void aguardaProximaJogada(Scanner sc) {
		sc.nextLine();
	}
	
	private static void encerraRodada() {
		System.out.println("\n");
		System.out.println("====================  Rodada Encerrada.  ======================");
		System.out.println("\n");
	}
	
	private static void iniciaRodada() {
		System.out.println("\n");
		System.out.println("====================  Início Rodada.  ======================");
		System.out.println("\n");		
	}

	private static void imprimeMaosJogadores(Jogador jogador1, Jogador jogador2) {
		
		System.out.println("Peças de " + jogador1.getNome() + " : " + jogador1.getPecasComoString() + "\n");
		System.out.println("Peças de " + jogador2.getNome() + " : " + jogador2.getPecasComoString() + "\n");
		
	}

	private static void impimeTabuleiro(Tabuleiro tabuleiro) {
		System.out.println("Tabuleiro: " + tabuleiro.toString() + "\n");
	}
	

}

