package presentation.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.RechercheClient;

import data.Client;


import exception.ExceptionClientInexistant;


@SuppressWarnings("serial")
public class PanelRechercheClient  extends JPanel implements ActionListener {
	JFrame parent;

	JButton btnRechercher = new JButton("Rechercher");
	JButton btnEffacer = new JButton("Effacer");
	
	JLabel lblNom = new JLabel("Nom");
	JLabel lblNumero = new JLabel("Numero");

	JTextField jtfNom = new JTextField();
	JTextField jtfNumero = new JTextField();

	JPanel panel = new JPanel(new GridLayout(3, 2));

	public PanelRechercheClient(JFrame pere) {
		parent = pere;

		setPreferredSize(new Dimension(200, 150));
		setLayout(new BorderLayout());

		btnRechercher.addActionListener(this);

		panel.setPreferredSize(new Dimension(200, 150));

		panel.add(lblNom);
		panel.add(jtfNom);
		panel.add(lblNumero);
		panel.add(jtfNumero);
		panel.add(btnRechercher);
		panel.add(btnEffacer);

		add(panel, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnRechercher) {
			if (jtfNom.getText().length() != 0 && jtfNumero.getText().length() != 0) {
				if (jtfNumero.getText().length() == 9) {
					String nom = jtfNom.getText();
					int numero = Integer.parseInt(jtfNumero.getText());
					try {
						//????????????
						Client c =  RechercheClient.rechercherClient(nom, numero);
						new FrameInformationsClient("Informations du client", c);
					} catch (ExceptionClientInexistant e1) {
						JOptionPane.showMessageDialog(parent,
								e1.getMessage(), "Erreur création",
								JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(parent,
								e2.getMessage(), "Erreur création",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(parent,
							"Le numéro doit faire 9 caractères");
				}
			} else {
				JOptionPane.showMessageDialog(parent,
						"Veuillez remplir tous les champs");
			}
		}
		
		if (e.getSource() == btnEffacer) {
			jtfNom.setText("");
			jtfNumero.setText("");
		}
	}
}
