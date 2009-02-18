package domino;

import domino.gui.GUI;

/**
 * Classe principal do Jogo de domino. Eh a responsavel pela
 * criacao do ambiente, inicializacao e controle das jogadas
 * do Jogo.
 * 
 * @author Allysson Makens
 *
 */
public class Jogo {

	private GUI listener;
	private String nome1;
	private String nome2;
	private int h1;
	private int h2;
	
	public Jogo(String nome1, String nome2, int h1, int h2){
		this.nome1 = nome1;
		this.nome2 = nome2;
		this.h1 = h1;
		this.h2 = h2;
	}
	
	
	public GUI getGUI(){
		if(listener == null ){
			listener = new GUI(nome1, nome2, h1, h2);
			new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		return (GUI)listener;
	}
}

