package domino.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import domino.Jogo;


/**
 * @author Allysson
 *
 */
public class IFrameJogar extends JInternalFrame{
	private static final long serialVersionUID = 6901761497648875351L;
    private JButton botaoJogar;
    private JLabel heuristica1;
    private JLabel heuristica2;
    private JLabel player1;
    private JLabel player2;
	private JRadioButton radioJog1Heur1;
	private JRadioButton radioJog1Heur2;
	private JRadioButton radioJog2Heur1;
	private JRadioButton radioJog2Heur2;
    private JTextField campoPlayer1;
    private JTextField campoPlayer2;
    private ButtonGroup radio1;
    private ButtonGroup radio2;
	private Main pai;
	private Jogo jogo;
    
    public IFrameJogar(Main pai){
    	super("Novo Jogo");
    	init(pai);
    	setupLayout();
    	setupListeners();
    	display();
    }
	
	private void init(Main pai) {
		this.pai = pai;
        botaoJogar = new JButton("Jogar");
        heuristica1 = new JLabel("Heurística Jogador 1");
        heuristica2 = new JLabel("Heurística Jogador 2");
        player1 = new JLabel("Jogador 1");
        player2 = new JLabel("Jogador 2");
        
        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        heuristica1.setHorizontalAlignment(SwingConstants.CENTER);
        heuristica2.setHorizontalAlignment(SwingConstants.CENTER);
        
        radioJog1Heur1 = new JRadioButton("Heuristica 1", true);
        radioJog1Heur2 = new JRadioButton("Heuristica 2");
        radioJog2Heur1 = new JRadioButton("Heuristica 1", true);
        radioJog2Heur2 = new JRadioButton("Heuristica 2");
        campoPlayer1 = new JTextField("Jogador 1");
        campoPlayer2 = new JTextField("Jogador 2");
        
        radio1 = new ButtonGroup();
        radio1.add(radioJog1Heur1);
        radio1.add(radioJog1Heur2);
        
        radio2 = new ButtonGroup();
        radio2.add(radioJog2Heur1);
        radio2.add(radioJog2Heur2);
	}
	private void setupLayout() {
		getContentPane().setLayout(new BorderLayout());
		
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(4,2,4,4));
		painel.add(player1, 0);
		painel.add(campoPlayer1,1);
		painel.add(player2, 2);
		painel.add(campoPlayer2,3);
		painel.add(heuristica1, 4);
		
		JPanel radioPanel = new JPanel(new GridLayout(1,2));
		radioPanel.add(radioJog1Heur1, 0);
		radioPanel.add(radioJog1Heur2, 1);
		
		painel.add(radioPanel, 5);
		
		painel.add(heuristica2, 6);
		
		JPanel radioPanel1 = new JPanel(new GridLayout(1,2));
		radioPanel1.add(radioJog2Heur1, 0);
		radioPanel1.add(radioJog2Heur2, 1);
		
		painel.add(radioPanel1, 7);		
		
		getContentPane().add(painel, BorderLayout.CENTER);
		getContentPane().add(botaoJogar, BorderLayout.SOUTH);
		painel.setBorder(BorderFactory.createLineBorder(Color.black));
		
	}
	private void setupListeners() {
		botaoJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
								String nome1 = campoPlayer1.getText();
								String nome2 = campoPlayer2.getText();
								iniciarJogo(nome1, nome2);
								pai.addJanela(jogo.getGUI());
								
						}
					});
					fechar();
			}
		});
	}
	private void display() {
		setClosable(false);
		setResizable(false);
		setSize(500,350);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void fechar() {
		pai.removeJanela(this);
		dispose();
	}
	
	private void iniciarJogo(String nome1, String nome2) {
		if(jogo != null){
			pai.removeJanela(jogo.getGUI());
		}
		int h1 = (radioJog1Heur1.isSelected()? 1 : 2);
		int h2 = (radioJog2Heur1.isSelected()? 1 : 2);
		jogo = new Jogo(nome1, nome2, h1, h2);
	}

	public void reiniciar() {
		if(!pai.contains(this)){
			pai.addJanela(this);
		}
		display();
	}
}
