package presentation.reservation;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FrameEffectuerReservation extends JFrame {

	JPanel panel;

	 public FrameEffectuerReservation(String title) {
		super(title);

		panel = new PanelEffectuerReservation(this);

		// configuration de la fenêtre
		setLocationRelativeTo(null);
		setSize(300, 500);
		// setDefaultCloseOperation();

		getContentPane().add(panel);
		getContentPane().setSize(300, 400);
		setLayout(new BorderLayout());
		// setDefaultCloseOperation();

		getContentPane().add(panel, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}
}
