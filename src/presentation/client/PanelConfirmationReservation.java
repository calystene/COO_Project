package presentation.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.ConfirmationPaiement;
import metier.RechercheClient;
import data.Reservation;
import data.forfait.Forfait;
import data.forfait.TYPE_FORFAIT;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientInexistant;
import exception.ExceptionForfaitInexistant;
import exception.ExceptionReservationInexistante;
import factory.FactoryForfait;

@SuppressWarnings("serial")
public class PanelConfirmationReservation extends JPanel implements ActionListener {
	
	JFrame parent;
	Reservation r;
	
	
	//Objet Panel
	JPanel panelPrincipale = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();

	//Objet panel1
	JLabel prix = new JLabel("Prix à payer : ");
	JLabel prixT = new JLabel("");
	JButton refresh = new JButton("Rafraichir");
	
	//Objet panel2
	JLabel nbrH = new JLabel("Nbr h gratuites :");
	JLabel nbrHT = new JLabel("");
	JLabel vide = new JLabel("");

	JButton utilCarte = new JButton("Utiliser");
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	//Objet panel3
	DefaultComboBoxModel<Integer> comboModel = new DefaultComboBoxModel<Integer>();
	JComboBox<Forfait> cbForfait = new JComboBox<Forfait>();
	JLabel lblHeureF = new JLabel();
	JLabel lblNumF = new JLabel("N° ");
	JButton utilForfait = new JButton("Utiliser");
	
	//Objet panel4
	JButton payer = new JButton("Confirmer le paiement");
	
	public PanelConfirmationReservation (JFrame pere, Reservation res) throws SQLException, ExceptionClientInexistant{
		parent = pere;
		r = res;
		
		//Panel1
		panel1.setLayout(new GridLayout(1, 3));
		panel1.setPreferredSize(new Dimension(500, 50));
		
		panel1.add(prix);
		prixT.setText(Float.toString(r.getPrix())+"€");
		panel1.add(prixT);
		panel1.add(refresh);
		
		panel1.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Prix"));
		
		refresh.addActionListener(this);
		
		panel1.setVisible(true);
		
		//Panel2
		panel2.setLayout(new GridLayout(2, 2));
		panel2.setPreferredSize(new Dimension(250, 80));
		
		panel2.add(nbrH);
		nbrHT.setText(Integer.toString(r.getClient().getCarteFidelite().getNbHeureGratuite())+" H");
		panel2.add(nbrHT);
		panel2.add(vide);
		panel2.add(utilCarte);

		
		panel2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Carte Fidélité"));
		
		utilCarte.addActionListener(this);
		
		panel2.setVisible(true);
		
		//Panel3

		for (Forfait f : FactoryForfait.getInstance().rechercherByClient(r.getClient())){
			if(r.getSalle().getTypeSalle()==TYPE_SALLE.PETITE_SALLE){
				if (f.getType()==TYPE_FORFAIT.A_PETITE || f.getType()==TYPE_FORFAIT.B_PETITE){
					comboModel.addElement(f.getNumero());
				}
			} else {
				if (f.getType()==TYPE_FORFAIT.A_GRANDE || f.getType()==TYPE_FORFAIT.B_GRANDE){
					comboModel.addElement(f.getNumero());
			}
		}
		}
		cbForfait = new JComboBox(comboModel);
		panel3.setLayout(new GridBagLayout());
		panel3.setPreferredSize(new Dimension(250, 80));
		
		int numForfait = Integer.valueOf(cbForfait.getSelectedItem().toString());
		try {
			Forfait f = FactoryForfait.getInstance().rechercherForfait(numForfait);
			lblHeureF.setText("Reste " + String.valueOf(f.getHeureDisponible()) + "h");
		} catch (SQLException | ExceptionForfaitInexistant
				| ExceptionClientInexistant e1) {
			e1.printStackTrace();
		}
		
		gbc.insets = new Insets(2, 10, 2, 10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		lblNumF.setPreferredSize(new Dimension(40,35));
		panel3.add(lblNumF);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		cbForfait.setPreferredSize(new Dimension(185,35));
		panel3.add(cbForfait, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		lblHeureF.setPreferredSize(new Dimension(60,35));
		panel3.add(lblHeureF, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		utilForfait.setPreferredSize(new Dimension(250,35));
		panel3.add(utilForfait, gbc);
		
		panel3.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Forfait"));
		
		cbForfait.addActionListener(this);
		utilForfait.addActionListener(this);
		
		panel3.setVisible(true);
		
		//Panel4
		panel4.setLayout(new GridLayout(1, 1));
		panel4.setPreferredSize(new Dimension(500, 50));
		
		panel4.add(payer);
		
		panel4.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Paiement"));
		
		payer.addActionListener(this);
		
		panel4.setVisible(true);
		
		//Ajout au panel Principale
		panelPrincipale.setLayout(new BorderLayout());
		panelPrincipale.setPreferredSize(new Dimension(500, 180));
		panelPrincipale.add(panel1, BorderLayout.NORTH);
		panelPrincipale.add(panel2, BorderLayout.WEST);
		panelPrincipale.add(panel3, BorderLayout.EAST);
		panelPrincipale.add(panel4, BorderLayout.SOUTH);

		
		//Ajout du panel à la frame principale
		setLayout(new BorderLayout());
		add(panelPrincipale);
	
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == refresh) {
			float prixR;
			try {
				prixR = ConfirmationPaiement.RafraichirPrix(r);
				prixT.setText(Float.toString(prixR)+"€");
			} catch (SQLException e1) {
			} catch (ExceptionReservationInexistante e1) {
			}
			
		}
		
		if (e.getSource() == utilCarte) {
			float prixR;
			try {				
				ConfirmationPaiement.utilisationCarte(r);
				prixR = ConfirmationPaiement.RafraichirPrix(r);
				prixT.setText(Float.toString(prixR)+"€");
				nbrHT.setText(Integer.toString(r.getClient().getCarteFidelite().getNbHeureGratuite())+" H");
			} catch (SQLException e1) {
			} catch (ExceptionReservationInexistante e1) {
			}
			
		}
		
		if(e.getSource() == cbForfait) {
			int numForfait = Integer.valueOf(cbForfait.getSelectedItem().toString());
			try {
				Forfait f = FactoryForfait.getInstance().rechercherForfait(numForfait);
				lblHeureF.setText("Reste " + String.valueOf(f.getHeureDisponible()) + "h");
			} catch (SQLException | ExceptionForfaitInexistant
					| ExceptionClientInexistant e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == utilForfait) {
			float prixR;

			try {		
				int numForfait = Integer.valueOf(cbForfait.getSelectedItem().toString());
				Forfait f = FactoryForfait.getInstance().rechercherForfait(numForfait);
				ConfirmationPaiement.utilisationForfait(r,f);
				prixR = ConfirmationPaiement.RafraichirPrix(r);
				prixT.setText(Float.toString(prixR)+"€");
				lblHeureF.setText("Reste " + String.valueOf(f.getHeureDisponible()) + "h");
			} catch (SQLException e1) {
			} catch (ExceptionReservationInexistante e1) {
			} catch (ExceptionForfaitInexistant e1) {
				e1.printStackTrace();
			} catch (ExceptionClientInexistant e1) {
				e1.printStackTrace();
			}
			
		}
		
		if (e.getSource() == payer) {
			r.setEtatPaiement(true);
			RechercheClient.majEtatPaiement(r);
			
			parent.dispose();
		}
	}

	
	
	
	
}
