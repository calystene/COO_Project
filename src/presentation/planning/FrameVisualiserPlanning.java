package presentation.planning;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameVisualiserPlanning extends JFrame {
	JPanel panel;
	
	public FrameVisualiserPlanning(String title) {
		super(title);
		
		panel = new PanelVisualiserPlanning(this);
		
		// configuration de la fenêtre
		setLocationRelativeTo(null);
		setSize(500, 600);
		
		getContentPane().add(panel);
		getContentPane().setSize(500, 600);
		setLayout(new BorderLayout());
		// setDefaultCloseOperation();

		getContentPane().add(panel, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}
}
