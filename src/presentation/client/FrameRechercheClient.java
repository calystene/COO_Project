package presentation.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FrameRechercheClient extends JFrame {
	/** procedure de lancement du programme 
	 * @throws Exception **/	
	
	JPanel panel;
	
	public FrameRechercheClient(String title) {
		super(title);
		panel = new PanelRechercheClient(this);
		
		// configuration de la fenêtre
		setSize(1100, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		getContentPane().add(panel,BorderLayout.CENTER);		
		pack();
		setVisible(true);
	}
}
