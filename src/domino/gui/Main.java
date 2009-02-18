package domino.gui;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;



/**
 * @author Allysson
 *
 */
public class Main extends JFrame{
    
	private static final long serialVersionUID = -2573324610038817445L;
    public JDesktopPane desktop;
    private JMenuBar menu;
	
    public Main() {
    	super("Domino");
    	init();
    	setupLayout();
    }
    
    private void init() {
        menu = new JMenuBar();
        
        buildMenu();
        desktop = new JDesktopPane();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

	private void buildMenu() {
		menu.add(new MenuJogo(this));
		menu.add(new MenuSobre(this));
		
		setJMenuBar(menu);
	}

	private void setupLayout() {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(desktop, BorderLayout.CENTER);
	}

	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

	public void addJanela(JInternalFrame frame) {
		desktop.add(frame);
	}

	public void sair() {
		System.exit(0);
	}

	public void removeJanela(JInternalFrame frame) {
		desktop.remove(frame);
		frame.dispose();
		desktop.repaint();
	}

	public boolean contains(IFrameJogar frameJogar) {
		JInternalFrame[] allFrames = desktop.getAllFrames();
		for(JInternalFrame frame : allFrames){
			if(frame == frameJogar){
				return true;
			}
		}
		return false;
	}

	public void minizarTodos() {
		JInternalFrame[] all = desktop.getAllFrames();
		for(JInternalFrame frame : all){
			try {
				frame.setIcon(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		
	}
    
}
