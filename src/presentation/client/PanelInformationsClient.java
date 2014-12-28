package presentation.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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

		// Panel contenant les infos du client
		GridLayout glInfos = new GridLayout(2,2);
		glInfos.setHgap(20); // gère l'espacement horizontal du grid layout
		panel.setLayout(glInfos);
		
		panel.add(lblNom);
		panel.add(lblPrenom);
		panel.add(lblTelephone);
		panel.add(lblIdentifiant);

		JPanel infosClient = new JPanel();
		infosClient.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Infos client"));

		infosClient.add(panel);
		
		
		
		// Panel contenant les autres infos
		JPanel infosReservation = new JPanel();
		infosReservation.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Infos réservations"));
		
		
		
		
		// Panel contenant les boutouns
		JPanel bouttons = new JPanel();
		bouttons.add(btnForfait);
		bouttons.add(btnReservation);
		
		
		
		
		add(infosClient, BorderLayout.NORTH);
		add(bouttons, BorderLayout.CENTER);
		add(infosReservation, BorderLayout.SOUTH);
		
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