package dominio;

/**
 * Classe que representa uma peca do Jogo de domino.
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
	
	public boolean equals(Peca p){
		return this.ladoDireito == p.getLadoDireito() &&
					this.ladoEsquerdo == p.getLadoEsquerdo();
	}
	
	public String toString(){
		return "|"+ladoEsquerdo + ":" + ladoDireito+"|";
	}
	
	private String getImage(){
		String resultado = "imagens/";
		if (ladoEsquerdo > ladoDireito){
			resultado += ladoDireito + "_" + ladoEsquerdo;
		} else {
			resultado += ladoEsquerdo + "_" + ladoDireito;
		}
		return resultado;
	}
	
	public String getImagemTabuleiro(){
		String resultado = "imagens/";
		if (ladoEsquerdo > ladoDireito){
			resultado += ladoDireito + "_" + ladoEsquerdo + "-180.png";
		} else {
			resultado += ladoEsquerdo + "_" + ladoDireito + ".png";
		}
		return resultado;
	}
	
	public String getImagemTabuleiroInvertido(){
		String resultado = "imagens/";
		if (ladoEsquerdo > ladoDireito){
			resultado += ladoDireito + "_" + ladoEsquerdo + ".png";
		} else {
			resultado += ladoEsquerdo + "_" + ladoDireito + "-180.png";
		}
		return resultado;
	}
	
	public String getImagemTabuleiroDeitado(){
		String resultado = "imagens/";
		if (ladoEsquerdo > ladoDireito){
			resultado += ladoDireito + "_" + ladoEsquerdo + "-270.png";
		} else {
			resultado += ladoEsquerdo + "_" + ladoDireito + "-90.png";
		}
		return resultado;
	}
	
	public String getImage0(){
		return getImage() + ".png";
	}
	
	public String getImage90(){
		return getImage() + "-90.png";
	}
	
	public String getImage180(){
		return getImage() + "-180.png";
	}
	
	public String getImage270(){
		return getImage() + "-270.png";
	}
	
}
