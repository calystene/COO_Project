package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentation.client.FrameCreerClient;

@SuppressWarnings("serial")
public class MenuPrincipal extends JPanel implements ActionListener {
	JFrame parent;
	
	JButton btnCreerClient = new JButton("Créer un client");
	JButton btnRechercherClient = new JButton("Rechercher un client");
	JButton btnVisualiserPlanning = new JButton("Visualiser le planning");
	JButton btnCreerReservation  = new JButton("Effectuer une réservation");
	
	
	public MenuPrincipal(JFrame p) {
		parent = p;
		
		//Configuration panel
		setPreferredSize(new Dimension(200,300));
		
		// Ajout des listener aux boutons
		btnCreerClient.addActionListener(this);
		btnRechercherClient.addActionListener(this);
		btnVisualiserPlanning.addActionListener(this);
		btnCreerReservation.addActionListener(this);
		
		// Création d'un panel et ajout des boutons pour organiser en 1 seule colonne
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.add(btnCreerClient);
		panel.add(btnRechercherClient);
		panel.add(btnVisualiserPlanning);
		panel.add(btnCreerReservation);
		
		setLayout(new BorderLayout());
		
		
		
		add(panel,BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCreerClient) {
			new FrameCreerClient("Création client");
		}
		
		if(e.getSource() == btnRechercherClient) {
			
		}
		
		if(e.getSource() == btnVisualiserPlanning) {
			
		}
		
		if(e.getSource() == btnCreerReservation) {
			
		}
	}
}
