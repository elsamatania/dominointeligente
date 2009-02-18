package domino.gui;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import util.DominoUtil;
import dominio.Peca;
import dominio.Tabuleiro;
import dominio.jogador.Jogador;
import dominio.jogador.JogadorFactory;
import domino.Jogo;

/**
 * @author Allysson
 *
 */
public class GUI extends JInternalFrame implements MouseMotionListener {

	private static final long serialVersionUID = -4881042099182388752L;
	private Jogo controlador;
	public Jogador jogador1;
	public Jogador jogador2;
	private Tabuleiro tabuleiro;
	private JButton botaoJogar;
	private JLabel situacaoJogo;
	private boolean jogador1Jogou;
	private boolean jogador2Jogou;
	private boolean jogou1;
	private boolean jogou2;
	private boolean jogoAcabou;
	private String nome1;
	private String nome2;
	private int h1;
	private int h2;
	
	public GUI(String nome1, String nome2, int h1, int h2){
		super("Domino");
		this.nome1 = nome1;
		this.nome2 = nome2;
		this.h1 = h1;
		this.h2 = h2;
		botaoJogar = new JButton("Jogar");
		situacaoJogo = new JLabel();
		atualizaSituacaoJogo("Inicia o jogo de dominó");
		jogador1Jogou = false;
		jogador2Jogou = false;
		jogou1 = false;
		jogou2 = false;
		jogoAcabou = false;
		setSize(1300,700);
		setVisible(true);
		getContentPane().addMouseMotionListener(this);
		addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {
				update();				
			}
			public void componentResized(ComponentEvent e) {
				update();
			}
			public void componentMoved(ComponentEvent e) {
				update();
				impimeTabuleiro(tabuleiro, getContentPane().getGraphics());
			}
			public void componentHidden(ComponentEvent e) {
				update();
			}
		});
		init();
		setupListeners();
	}
	
	private void init(){
		jogador1 = JogadorFactory.getInstance().getJogador(h1, nome1);
		
		jogador2 = JogadorFactory.getInstance().getJogador(h2, nome2);
		
		tabuleiro = new Tabuleiro();
		try {
			inicializacaoJogo(jogador1, jogador2, tabuleiro);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void setupListeners() {
		botaoJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (!jogoAcabou){
								if (!jogou1){
									paint(getContentPane().getGraphics(), controlador);
									jogarJogador1(getContentPane().getGraphics());
								}else if (!jogou2){
									paint(getContentPane().getGraphics(), controlador);
									jogarJogador2(getContentPane().getGraphics());
								}
								if (jogou1 && jogou2){
									verficaFimJogo();
								}
							}
						}
					});
			}
		});
	}

	public Jogo getControlador() {
		return controlador;
	}

	public void iniciou(Jogo controlador) {
		this.controlador = controlador;
		Graphics g = getContentPane().getGraphics();
		paint(g);
	}

	public void paint(Graphics g, Jogo controlador) {
		limpaTela(g);
		printPecasJogador1(g);
		printPecasJogador2(g);
		getContentPane().add(botaoJogar, BorderLayout.SOUTH);
	}
	
	private void atualizaSituacaoJogo(String mensagem){
		situacaoJogo.setText("  " + mensagem);
		getContentPane().add(situacaoJogo,BorderLayout.NORTH);
	}

	public void limpaTela(Graphics g) {
		if(g != null){
			Image image = new ImageIcon("imagens/telabranca.JPG").getImage();
			g.drawImage(image, 0, 20, this);
		}
	}
	
	public void printPecasJogador1(Graphics g) {
		int x = 140;
		for (int i = 0; i < jogador1.getQuantPecas(); i++) {
			Peca peca = jogador1.getPeca(i);
			ImageIcon image = new ImageIcon(peca.getImage90());
			image.paintIcon(this, g, x, 60);
			x += 40;
		}
		this.getGraphics().drawString(jogador1.getNome(), 60, 150);
	}
	
	public void printPecasJogador2(Graphics g) {
		int x = 140;
		for (int i = 0; i < jogador2.getQuantPecas(); i++) {
			Peca peca = jogador2.getPeca(i);
			ImageIcon image = new ImageIcon(peca.getImage90());
			image.paintIcon(this, g, x, 540);
			x += 40;
		}
		this.getGraphics().drawString(jogador2.getNome(), 60, 630);
	}


	/**
	 * 
	 */
	private void update() {
		paint(getContentPane().getGraphics(), controlador);
	}
	
	public void verficaFimJogo() {
			
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
		jogou1 = false;
		jogou2 = false;
		}
	
	private void jogarJogador1(Graphics g){
		// realiza jogada do jogador1
		Peca pecaJogada = jogador1.joga(tabuleiro);
		if(pecaJogada != null)
			jogador1Jogou = true;
		jogou1 = true;
		
		imprimePecaJogada(jogador1, pecaJogada, g);
		impimeTabuleiro(tabuleiro, g);
		
		if(jogador1.bateu()){
			jogoAcabou = true;
			ImprimeFimDeJogo(jogador1);
		}
	}
	
	private void jogarJogador2(Graphics g){
		// realiza jogada do jogador2
		Peca pecaJogada = jogador2.joga(tabuleiro);
		if(pecaJogada != null)
			jogador2Jogou = true;
		jogou2 = true;
		
		imprimePecaJogada(jogador2, pecaJogada, g);
		impimeTabuleiro(tabuleiro, g);
		
		if(jogador2.bateu()){
			jogoAcabou = true;
			ImprimeFimDeJogo(jogador2);
		}
		
	}
	
	private void ImprimeFimDeJogoPorPontos(Jogador vencedor, Jogador perdedor) {
		String mensagem = "FIM DE JOGO: VITORIA POR PONTOS. VENCEDOR: " + vencedor.getNome() + " COM " + vencedor.getTotalPontos() + " PONTOS; " +
		"PERDEDOR: " + perdedor.getNome() + " COM " + perdedor.getTotalPontos() + " PONTOS.";
		atualizaSituacaoJogo(mensagem);
	}
	
	private void ImprimeJogoEmpatado(int pontosJogador) {
		String mensagem = "FIM DE JOGO: JOGO EMPATADO. PONTOS DOS JOGADORES: " + pontosJogador;
		atualizaSituacaoJogo(mensagem);
		
	}
	
	private void ImprimeFimDeJogo(Jogador jogadorVencedor) {
		String mensagem = "FIM DE JOGO. VITORIA POR BATIDA. VENCEDOR: " + jogadorVencedor.getNome();
		atualizaSituacaoJogo(mensagem);
	}
	
	private void imprimePecaJogada(Jogador jogador, Peca pecaJogada, Graphics g) {
		String mensagem = "Jogador " + jogador.getNome() + " joga, ";
		if(pecaJogada == null)
			mensagem += "PASSOU A VEZ!";
		else
			mensagem += "Peça jogada: " + pecaJogada.toString();
		atualizaSituacaoJogo(mensagem);
	}
	
	private void inicializacaoJogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) throws InterruptedException {
		tabuleiro.instanciaTodasAsPecas();
		Set<Peca> todasAsPecas = tabuleiro.getTodasAsPecas();
		DominoUtil.misturaPecas(todasAsPecas);
		Thread.sleep(1000);
		
		DominoUtil.distribuiPecas(jogador1, jogador2, todasAsPecas);
		Thread.sleep(1000);
	}
	
	private void impimeTabuleiro(Tabuleiro tabuleiro, Graphics g) {
		int x = 20;
		int y = 340;
		int i = 0;
		for (Peca peca : tabuleiro.getPecasJogadas()) {
			i++;
			if (i == 15){
				ImageIcon image = new ImageIcon(peca.getImagemTabuleiroDeitado());
				image.paintIcon(this, g, x, y);
				y += 80;
				x -= 40;
			}else if (i < 15){
				ImageIcon image = new ImageIcon(peca.getImagemTabuleiro());
				image.paintIcon(this, g, x, y);
				x += 80;
			}else{
				ImageIcon image = new ImageIcon(peca.getImagemTabuleiroInvertido());
				image.paintIcon(this, g, x, y);
				x -= 80;
			}
		}
	}
	
	public Jogador getJogador1() {
		return jogador1;
	}
	
	public Jogador getJogador2() {
		return jogador2;
	}
	
	/**
	 * Quando o mouse é pressionado na janela
	 */
	public void mousePressed(MouseEvent arg0) {
	}

	/**
	 * quando o mouse é solto na janela
	 */
	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseDragged(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Quando o mouse é arrastado na janela
	 */

	public void mouseMoved(MouseEvent arg0) {
	}
}
