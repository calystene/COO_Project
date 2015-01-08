package presentation.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")


public class FrameCreerClient extends JFrame {
	
	JPanel panel;
	
	public FrameCreerClient(String title) {
		super(title);
		
		panel = new PanelCreerClient(this);
		
		// configuration de la fenêtre
		setLocationRelativeTo(null);
		setSize(300, 500);
		getContentPane().setSize(300,100);
		getContentPane().add(panel);
		setLayout(new BorderLayout());
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
}
