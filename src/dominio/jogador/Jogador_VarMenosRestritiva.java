package dominio.jogador;

import java.util.LinkedList;

import dominio.Peca;
import dominio.Tabuleiro;


/**
 * Esta classe implementa o tipo de jogador que utiliza a estrategia de
 * "Variavel Menos Restritiva" para realizar as suas jogadas.
 * 
 * Variavel Menos Restritiva: Joga a peca que deixa mais opções para escolhas no futuro.
 * 
 * @author Gustavo Farias
 *
 */
public class Jogador_VarMenosRestritiva extends Jogador {

	public Jogador_VarMenosRestritiva(String nome){
		super(nome);
	}

	@Override
	public Peca joga(Tabuleiro tabuleiro) {

		Peca pecaJogada = null;
		
		// se eh inicio de jogo, joga a peca menos restritiva da mao
		if(tabuleiro.estaVazio()){
			int ladoMaisRepetido = getLadoMaisFrequente();
			LinkedList<Peca> pecasCompativeisComLado = getPecasCompativeisComUmLado(ladoMaisRepetido);
			pecaJogada = selecionaPecaMenosRestritiva(ladoMaisRepetido, pecasCompativeisComLado);
			
			if(pecaJogada != null){
				adicionaPeca(tabuleiro, pecaJogada);
				removePeca(pecaJogada);
			}
			
			return pecaJogada;
		}
		
		int ladoEsqPecaEsquerdaTabuleiro = tabuleiro.getPecaEsquerdaTabuleiro().getLadoEsquerdo();
		int ladoDirPecaDireitaTabuleiro = tabuleiro.getPecaDireitaTabuleiro().getLadoDireito();
		
		// Obtem a quant. de pecas compativeis com cada um dos lados do tabuleiro.
		LinkedList<Peca> pecasCompativeisComLadoEsq = getPecasCompativeisComUmLado(ladoEsqPecaEsquerdaTabuleiro);
		LinkedList<Peca> pecasCompativeisComLadoDir = getPecasCompativeisComUmLado(ladoDirPecaDireitaTabuleiro);
		
		// Do lado com amis pecas compativeis, escolhe a peca mais compativel. Isto eh feito
		// analisando o outro lado de cada peca obtida acima. 
		// Sera jogada a peca que tiver o segundo lado mais compativel com outras pecas.
		if(pecasCompativeisComLadoEsq.size() > pecasCompativeisComLadoDir.size()){
			pecaJogada = selecionaPecaMenosRestritiva(ladoEsqPecaEsquerdaTabuleiro, pecasCompativeisComLadoEsq);
		} else {
			pecaJogada = selecionaPecaMenosRestritiva(ladoDirPecaDireitaTabuleiro, pecasCompativeisComLadoDir);
		}
		
		if(pecaJogada != null){
			adicionaPeca(tabuleiro, pecaJogada);
			removePeca(pecaJogada);
		}

		
		return pecaJogada;
	}

	/**
	 * Tenta adicionar a peca no tabuleiro, primeiro no lado esquerdo, depois no lado direito.
	 * @param tabuleiro
	 * @param peca
	 */
	private void adicionaPeca(Tabuleiro tabuleiro, Peca peca) {
		boolean adicionouLadoEsquerdo = tabuleiro.adicionaPecaEsquerdaTabuleiro(peca);
		if(!adicionouLadoEsquerdo)
			tabuleiro.adicionaPecaDireitaTabuleiro(peca);
	}

	/**
	 * Retorna o lado que mais ocorre nas pecas que estao
	 * na mao do jogador.
	 * 
	 * @return o lado que amis ocorre nas pecas que estao
	 * na mao do jogador.
	 */
	private int getLadoMaisFrequente() {
		
		int[] contador = new int[7]; // 0 a 6
		
		for (int i = 0; i < getQuantPecas(); i++) {
			Peca pecaAtual = getPeca(i);
			contador[pecaAtual.getLadoEsquerdo()] = contador[pecaAtual.getLadoEsquerdo()] + 1;
			contador[pecaAtual.getLadoDireito()] = contador[pecaAtual.getLadoDireito()] + 1;
		}
		
		int maiorNumero = contador[0];
		int lado = 0;
		for (int i = 1; i < contador.length; i++) {
			if(contador[i] > maiorNumero){
				maiorNumero = contador[i];
				lado = i;
			}
		}
		
		return lado;
	}

	/**
	 * Dada uma lista de pecas, onde todas tem um lado comum, utiliza o lado incomum
	 * para selecionar a peca mais compativel com as outras pecas da mao do jogador, isto eh,
	 * a peca que menos restringe jogadas futuras.
	 * 
	 * @param ladoComum
	 * @param pecas
	 * @return
	 */
	private Peca selecionaPecaMenosRestritiva(int ladoComum, LinkedList<Peca> pecas) {
		
		int maxPecasCompativeis = 0;
		Peca pecaEscolhida = null;
		
		for (int i = 0; i < pecas.size(); i++) {
			Peca peca = pecas.get(i);
			int quantPecasCompativeis = getPecasCompativeisComUmLado(peca.getLadoEsquerdo() == ladoComum ? 
												peca.getLadoDireito() : peca.getLadoEsquerdo()).size();
			
			if(quantPecasCompativeis > maxPecasCompativeis){
				maxPecasCompativeis = quantPecasCompativeis;
				pecaEscolhida = peca;
			}
				
		}
		
		return pecaEscolhida;
	}

	/**
	 * Procura na mao do jodagor todas as pecas que sao compativeis com o valor recebido.
	 * @param valor
	 * @return a lista de pecas compativeis.
	 */
	private LinkedList<Peca> getPecasCompativeisComUmLado(int valor) {

		LinkedList<Peca> pecasCompativeis = new LinkedList<Peca>();
		
		for (int i = 0; i < getQuantPecas(); i++) {
			Peca pecaAtual = getPeca(i);
			if(pecaAtual.getLadoEsquerdo() == valor || pecaAtual.getLadoDireito() == valor)
				pecasCompativeis.addLast(pecaAtual);
		}
		
		return pecasCompativeis;
	}
	
}
