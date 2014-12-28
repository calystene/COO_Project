package presentation.reservation;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelResaMulti extends JPanel {
	public PanelResaMulti() {
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Réservation sur plusieurs jours"));
	}
}
