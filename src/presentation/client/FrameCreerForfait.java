package presentation.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Client;

@SuppressWarnings("serial")
public class FrameCreerForfait extends JFrame {
	
	JPanel panel;
	
	public FrameCreerForfait(String title, Client c) {
		super(title);
		
		panel = new PanelCreerForfait(this,c);
		
		// configuration de la fenêtre
		setLocationRelativeTo(null);
		setSize(300, 500);
		getContentPane().setSize(900,80);
		getContentPane().add(panel);
		setLayout(new BorderLayout());
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
}
