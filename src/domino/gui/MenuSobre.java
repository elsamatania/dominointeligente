package domino.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author Allysson
 *
 */
public class MenuSobre extends JMenu {

	private static final long serialVersionUID = 5927369208071019826L;
	private JMenuItem sobre;
	private Main pai;
	
	public MenuSobre(Main pai){
		super("Sobre");
		this.pai = pai;
		
		sobre = new JMenuItem("Sobre");
		add(sobre);
		initListeners();
	}

	private void initListeners() {
		sobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.minizarTodos();
				pai.addJanela(new IFrameSobre(pai));
			}
		});
		
	}
}
