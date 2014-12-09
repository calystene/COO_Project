package presentation.client;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class FrameCreerClient extends JFrame {
	/** procedure de lancement du programme 
	 * @throws Exception **/	
	
	JPanel panel;
	
	public FrameCreerClient(String title) {
		super(title);
		
		panel = new PanelCreerClient(this);
		
		// configuration de la fenêtre
		setLocationRelativeTo(null);
		setSize(300, 500);
		getContentPane().setSize(300,500);
		//setDefaultCloseOperation();
		
		getContentPane().add(panel);
		
		pack();
		setVisible(true);
	}//fin main
}
