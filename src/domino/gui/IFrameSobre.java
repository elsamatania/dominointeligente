package domino.gui;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

public class IFrameSobre extends JInternalFrame {

	private static final long serialVersionUID = 8963893916973921335L;
	private TextArea area;
	private JButton fechar;
	private Main pai;
	
	public IFrameSobre(Main pai){
		super("Sobre");
		init(pai);
		setupLayout();
		setupListeners();
	}

	private void init(Main pai) {
		this.pai = pai;
		area = new TextArea();
		area.setEnabled(true);
		area.setEditable(false);
		fechar = new JButton("Fechar");
		
		initText();
		
		setClosable(false);
		setResizable(false);
		setVisible(true);
		setSize(500,500);
	}
	private void initText(){
		try {
			File f = new File("about.txt");
			BufferedReader in = new BufferedReader(new FileReader(f));
			String linha = "";
			while ((linha = in.readLine()) != null) {
				area.append(linha + System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setupLayout() {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(area, BorderLayout.CENTER);
		getContentPane().add(fechar, BorderLayout.SOUTH);
	}

	private void setupListeners() {
		fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}

		});
	}
	private void fechar() {
		pai.removeJanela(this);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				dispose();
			}
		});
	}
	
}
