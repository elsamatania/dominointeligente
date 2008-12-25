package dominio;

/**
 * Classe que representa uma peca do jogo de domino.
 * @author Gustavo Farias
 *
 */
public class Peca {

	/**
	 * Valor do lado esquerdo de uma peca de domino
	 */
	private int ladoEsquerdo;
	/**
	 * Valor do lado direito de uma peca de domino
	 */
	private int ladoDireito;
	
	public Peca(int ladoEsquerdo, int ladoDireito){
		this.ladoEsquerdo = ladoEsquerdo;
		this.ladoDireito = ladoDireito;
	}

	public int getLadoEsquerdo() {
		return ladoEsquerdo;
	}

	public void setLadoEsquerdo(int ladoEsquerdo) {
		this.ladoEsquerdo = ladoEsquerdo;
	}

	public int getLadoDireito() {
		return ladoDireito;
	}

	public void setLadoDireito(int ladoDireito) {
		this.ladoDireito = ladoDireito;
	}
	
}
