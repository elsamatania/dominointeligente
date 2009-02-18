package domino.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author Allysson
 *
 */
public class MenuJogo extends JMenu {

	private static final long serialVersionUID = -263571857172937618L;
	private Main parent;
	private JMenuItem novoJogo;
	private JMenuItem sair;
	private IFrameJogar jogo;
	
	public MenuJogo(Main pai){
		super("Jogo");
		this.parent = pai;
		
		novoJogo = new JMenuItem("Novo");
		sair = new JMenuItem("Sair");
		
		add(novoJogo);
		add(sair);
		initListeners();
	}

	private void initListeners() {
		novoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jogo == null){
					jogo = new IFrameJogar(parent);
					parent.addJanela(jogo);
				}
				else{
					jogo.reiniciar();
				}
			}
		});
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.sair();
			}
		});
	}
	
}
