package domino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import util.DominoUtil;
import dominio.Peca;
import dominio.Tabuleiro;
import dominio.jogador.Jogador;
import dominio.jogador.JogadorFactory;

/**
 * Classe prinipal do Jogo de domino. Eh a responsavel pela
 * criacao do ambiente, inicializacao e controle das jogadas
 * do Jogo.
 * 
 * @author Gustavo Farias
 *
 */
public class Jogo {
	
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader	inReader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Este jogo de domino será jogado por 2 jogadores virtuais. Cada jogador\n" + 
				"irá utilizar uma estratégia escolhida antes de o jogo começar. Vamos agora à\n" +
				"criação de cada jogador e à escolha de suas estratégias.\n");
		
		System.out.println("Digite o nome do Jogador 1: ");
		String nomeJogador1 = inReader.readLine();
		System.out.println("Escolha a estrategia de " + nomeJogador1 + " :");
		imprimeMenuEscolhaEstrategia();
		int estrategiaJogador1 = Integer.parseInt(inReader.readLine());
		Jogador jogador1 = JogadorFactory.getInstance().getJogador(estrategiaJogador1, nomeJogador1);
		
		System.out.println("Digite o nome do Jogador 2: ");
		String nomeJogador2 = inReader.readLine();
		System.out.println("Escolha a estrategia de " + nomeJogador2 + " :");
		imprimeMenuEscolhaEstrategia();
		int estrategiaJogador2 = Integer.parseInt(inReader.readLine());
		Jogador jogador2 = JogadorFactory.getInstance().getJogador(estrategiaJogador2, nomeJogador2);
		
		Tabuleiro tabuleiro = new Tabuleiro();
		inicializacaoJogo(jogador1, jogador2, tabuleiro);
		
		boolean jogoAcabou = false;
		
		// a cada iteracao no loop ocorrem 2 jogadas, uma de cada jogador.
		while(!jogoAcabou){
			
			imprimeInicioRodada();
			
			System.out.println("Jogador " + jogador1.getNome() + " joga.\n");
			aguardaProximaJogada(inReader);
			Peca pecaJogada = jogador1.joga(tabuleiro);
			imprimePecaJogada(pecaJogada);
			impimeTabuleiro(tabuleiro);
			imprimeMaosJogadores(jogador1, jogador2);
			
			if(jogador1.bateu()){
				ImprimeFimDeJogo(jogador1);
			}
				

			
			System.out.println("Jogador " + jogador2.getNome() + " joga.\n");
			aguardaProximaJogada(inReader);
			pecaJogada = jogador2.joga(tabuleiro);
			imprimePecaJogada(pecaJogada);
			impimeTabuleiro(tabuleiro);
			imprimeMaosJogadores(jogador1, jogador2);
			
			if(jogador2.bateu()){
				ImprimeFimDeJogo(jogador2);
			}
			
			ImprimeRodadaEncerrada();
			
			// jogo verifica se o jogo esta congelado e, no caso positivo, conta os pontos.
			
		}
		
	}
	
	// =====================================================================
	// ========== Metodos que imprimem informacoes sobre o jogo. ===========
	// =====================================================================

	private static void imprimePecaJogada(Peca pecaJogada) {
		if(pecaJogada == null)
			System.out.println("PASSOU A VEZ!\n");
		else
			System.out.println("Peça jogada: " + pecaJogada.toString() + "\n");
	}

	private static void inicializacaoJogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) throws InterruptedException {

		System.out.println("Criando e misturando as peças...");
		tabuleiro.instanciaTodasAsPecas();
		Set<Peca> todasAsPecas = tabuleiro.getTodasAsPecas();
		DominoUtil.misturaPecas(todasAsPecas);
		Thread.sleep(1000);
		
		System.out.println("Distribuindo entre os jogadores...\n");
		DominoUtil.distribuiPecas(jogador1, jogador2, todasAsPecas);
		Thread.sleep(1000);
		
		System.out.println("Peças de " + jogador1.getNome() + ": " + jogador1.getPecasComoString() + "\n");
		
		System.out.println("Peças de " + jogador2.getNome() + ": " + jogador2.getPecasComoString() + "\n");
		
		System.out.println("Tecle <ENTER> para que cada nova jogada seja realizada. " +
				"O jogador " + jogador1.getNome() + " começará o jogo.\n");
		
	}

	private static void imprimeMenuEscolhaEstrategia() {
		
		System.out.println("1 - Busca Cega: o jogador irá percorrer suas peças da esquerda para a direita \n" +
				"e jogar a primeira que se encaixar no tabuleiro.");
		
		System.out.println("2 - ");
		
	}

	private static void aguardaProximaJogada(BufferedReader inReader) throws IOException {
		inReader.readLine();
	}
	
	private static void ImprimeFimDeJogo(Jogador jogadorVencedor) {
		System.out.println("\n");
		System.out.println("====================  FIM DE JOGO.  ======================");
		System.out.println("VENCEDOR: " + jogadorVencedor);
		System.out.println("==========================================================");
		System.out.println("\n");
	}
	
	private static void ImprimeRodadaEncerrada() {
		System.out.println("\n");
		System.out.println("====================  Rodada Encerrada.  ======================");
		System.out.println("\n");
	}
	
	private static void imprimeInicioRodada() {
		System.out.println("\n");
		System.out.println("====================  Início Rodada.  ======================");
		System.out.println("\n");		
	}

	private static void imprimeMaosJogadores(Jogador jogador1, Jogador jogador2) {
		
		System.out.println("Peças de " + jogador1.getNome() + " : " + jogador1.getPecasComoString() + "\n");
		System.out.println("Peças de " + jogador2.getNome() + " : " + jogador2.getPecasComoString() + "\n\n");
		
	}

	private static void impimeTabuleiro(Tabuleiro tabuleiro) {
		System.out.println("Tabuleiro: " + tabuleiro.toString() + "\n");
	}
	

}

