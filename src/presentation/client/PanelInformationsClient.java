package presentation.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;



import data.Client;


@SuppressWarnings("serial")
public class PanelInformationsClient extends JPanel implements ActionListener {
	JFrame parent;


	
	JButton btnReservation = new JButton("Voir les reservations");
	JButton btnForfait = new JButton("Voir les forfaits");
	
	JPanel panel = new JPanel(new GridLayout(5, 2));
	
	public PanelInformationsClient(JFrame pere, Client c) {
		JLabel lblNom = new JLabel("Nom : " + c.getNom());
		JLabel lblPrenom = new JLabel("Prenom : " + c.getPrenom());
		JLabel lblTelephone = new JLabel("Telephone : 0" + c.getNumero());
		JLabel lblIdentifiant = new JLabel("Identifiant : " + c.hashCode());
		parent = pere;

		setPreferredSize(new Dimension(400, 200));
		setLayout(new BorderLayout());

		btnReservation.addActionListener(this);

		btnForfait.addActionListener(this);

		panel.setPreferredSize(new Dimension(400, 200));

		panel.add(lblNom);
		panel.add(lblPrenom);
		panel.add(lblTelephone);
		panel.add(lblIdentifiant);
		panel.add(btnReservation);
		panel.add(btnForfait);


		add(panel, BorderLayout.CENTER);

	}
	
	public void actionPerformed(ActionEvent e, Client c) {

		if (e.getSource() == btnReservation) {
			new FrameDetailsReservationClient("Liste des réservations", c);
		}
		
		if (e.getSource() == btnForfait) {
			new FrameDetailsForfaitClient("Liste des forfaits", c);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}