package presentation.reservation;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class PanelResaAuto extends JPanel {
	
	public PanelResaAuto() {
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Réservation automatique"));
	}
}
