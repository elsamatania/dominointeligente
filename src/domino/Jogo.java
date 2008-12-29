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
		boolean jogador1Jogou = false;
		boolean jogador2Jogou = false;
		
		// a cada iteracao no loop ocorrem 2 jogadas, uma de cada jogador.
		while(!jogoAcabou){
			
			imprimeInicioRodada();
			
			// realiza jogada do jogador1
			System.out.println("Jogador " + jogador1.getNome() + " joga.\n");
			aguardaProximaJogada(inReader);
			Peca pecaJogada = jogador1.joga(tabuleiro);
			if(pecaJogada != null)
				jogador1Jogou = true;
			
			imprimePecaJogada(pecaJogada);
			impimeTabuleiro(tabuleiro);
			imprimeMaosJogadores(jogador1, jogador2);
			
			if(jogador1.bateu()){
				ImprimeFimDeJogo(jogador1);
			}
				

			// realiza jogada do jogador2
			System.out.println("Jogador " + jogador2.getNome() + " joga.\n");
			aguardaProximaJogada(inReader);
			pecaJogada = jogador2.joga(tabuleiro);
			if(pecaJogada != null)
				jogador2Jogou = true;
			
			imprimePecaJogada(pecaJogada);
			impimeTabuleiro(tabuleiro);
			imprimeMaosJogadores(jogador1, jogador2);
			
			if(jogador2.bateu()){
				ImprimeFimDeJogo(jogador2);
			}
			
			ImprimeRodadaEncerrada();
			
			// Se o jogo esta trancado, conta os pontos e verifica
			// quem eh o vencedor ( quem fez menos pontos ).
			if(!jogador1Jogou && !jogador2Jogou){
				jogoAcabou = true;
				int pontosJogador1 = jogador1.getTotalPontos();
				int pontosJogador2 = jogador2.getTotalPontos();
				
				if(pontosJogador1 < pontosJogador2)
					ImprimeFimDeJogoPorPontos(jogador1, jogador2);
				else if(pontosJogador2 < pontosJogador1)
					ImprimeFimDeJogoPorPontos(jogador2, jogador1);
				else 
					ImprimeJogoEmpatado(pontosJogador1);
			}
			
			jogador1Jogou = false;
			jogador2Jogou = false;
			
		}
		
	}
	
	// =====================================================================
	// ========== Metodos que imprimem informacoes sobre o jogo. ===========
	// =====================================================================
	
	private static void ImprimeFimDeJogoPorPontos(Jogador vencedor, Jogador perdedor) {
		System.out.println("\n");
		System.out.println("====================  FIM DE JOGO: VITORIA POR PONTOS!! ======================");
		System.out.println("VENCEDOR: " + vencedor.getNome() + " COM " + vencedor.getTotalPontos() + " PONTOS;");
		System.out.println("PERDEDOR: " + perdedor.getNome() + " COM " + perdedor.getTotalPontos() + " PONTOS.");
		System.out.println("==========================================================");
		System.out.println("\n");
	}

	private static void ImprimeJogoEmpatado(int pontosJogador) {
		System.out.println("\n");
		System.out.println("====================  FIM DE JOGO: JOGO EMPATADO!!  ======================");
		System.out.println("PONTOS DOS JOGADORES: " + pontosJogador);
		System.out.println("==========================================================");
		System.out.println("\n");
		
	}
	
	private static void ImprimeFimDeJogo(Jogador jogadorVencedor) {
		System.out.println("\n");
		System.out.println("====================  FIM DE JOGO. VITORIA POR BATIDA!!  ======================");
		System.out.println("VENCEDOR: " + jogadorVencedor.getNome());
		System.out.println("==========================================================");
		System.out.println("\n");
	}

	private static void aguardaProximaJogada(BufferedReader inReader) throws IOException {
		inReader.readLine();
	}
	
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
		
		System.out.println("2 - Variável Menos Restritiva: o jgador escolhe a peça que deixa mais opções \n" +
				"para escolhas no futuro.");
		
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

