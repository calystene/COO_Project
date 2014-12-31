package presentation.reservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import metier.CreerReservation;
import data.horaire.PlageHoraire;
import data.horaire.TRANCHE;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientInexistant;
import exception.ExceptionCreneauNonDisponible;
import exception.ExceptionJourFerie;
import exception.ExceptionPlageInexistante;
import exception.ExceptionSalleInexistante;

public class PanelResaAuto extends JPanel implements ActionListener {
	JLabel lblDate;
	JLabel lblInfoDate;
	JLabel lblTrancheH;
	JLabel lblInfosTranche;
	JLabel lblTypeSalle;
	JLabel lblInfosSalle;
	JLabel lblDuree;

	JComboBox<TRANCHE> cbTrancheH;
	JComboBox<TYPE_SALLE> cbTypeSalle;
	JTextField jtfDate;
	JTextField jtfDuree;

	JButton btnRechercher;

	GridBagConstraints gbc;

	public PanelResaAuto() {
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black),
				"Réservation automatique"));
		// setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(500, 250));
		gbc = new GridBagConstraints();

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setPreferredSize(new Dimension(450, 200));

		// Initialisation des composants du panel
		lblDate = new JLabel("Date réservation");
		lblDate.setPreferredSize(new Dimension(100, 25));
		
		lblInfoDate = new JLabel("JJ-MM-AAAA");
		lblInfoDate.setPreferredSize(new Dimension(150,25));
		
		lblTrancheH = new JLabel("Tranche horaire");
		lblTrancheH.setPreferredSize(new Dimension(100, 25));

		lblInfosTranche = new JLabel("[9h,13h]");
		lblInfosTranche.setPreferredSize(new Dimension(150, 25));

		lblTypeSalle = new JLabel("Type de salle");
		lblTypeSalle.setPreferredSize(new Dimension(100, 25));

		lblInfosSalle = new JLabel("1h : 7€ / 2h : 10€");
		lblInfosSalle.setPreferredSize(new Dimension(150, 25));

		lblDuree = new JLabel("Duree");
		lblDuree.setPreferredSize(new Dimension(100, 25));

		TRANCHE[] tranche = { TRANCHE.MATIN, TRANCHE.AM, TRANCHE.SOIR };
		cbTrancheH = new JComboBox<TRANCHE>(tranche);
		cbTrancheH.setPreferredSize(new Dimension(125, 25));
		cbTrancheH.addActionListener(this);

		TYPE_SALLE[] salle = { TYPE_SALLE.PETITE_SALLE,	TYPE_SALLE.GRANDE_SALLE, TYPE_SALLE.SPECIFIQUE_SALLE };
		cbTypeSalle = new JComboBox<TYPE_SALLE>(salle);
		cbTypeSalle.setPreferredSize(new Dimension(125, 25));
		cbTypeSalle.addActionListener(this);

		jtfDate = new JTextField();
		jtfDate.setPreferredSize(new Dimension(125, 25));

		jtfDuree = new JTextField();
		jtfDuree.setPreferredSize(new Dimension(125, 25));

		btnRechercher = new JButton("Rechercher");
		btnRechercher.setPreferredSize(new Dimension(125, 25));
		btnRechercher.addActionListener(this);


		// gbc.gridheight = 1;
		// gbc.gridwidth = 1;
		// gbc.ipadx = 1;
		// gbc.ipady = 1;
		// gbc.weightx = 1.0;
		// gbc.weighty = 1.0;

		gbc.insets = new Insets(3, 5, 3, 5);
		
		// Première ligne
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblDate, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(jtfDate, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		panel.add(lblInfoDate);
		
		// Deuxieme ligne
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(lblTrancheH, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(cbTrancheH, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		panel.add(lblInfosTranche, gbc);

		// Troisieme ligne
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(lblTypeSalle, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(cbTypeSalle, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		panel.add(lblInfosSalle, gbc);

		// Quatrieme ligne
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblDuree, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(jtfDuree, gbc);

		// Cinquieme ligne
		gbc.gridx = 2;
		gbc.gridy = 4;
		panel.add(btnRechercher, gbc);

		add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cbTrancheH) {
			switch((TRANCHE) cbTrancheH.getSelectedItem()) {
			case MATIN:
				lblInfosTranche.setText("[9h,13h]");
				break;
			case AM:
				lblInfosTranche.setText("[13h,20h]");
				break;
			case SOIR:
				lblInfosTranche.setText("[20h,00h]");
				break;
			}
		}
		
		if(e.getSource() == cbTypeSalle) {
			switch((TYPE_SALLE) cbTypeSalle.getSelectedItem()) {
			case PETITE_SALLE:
				lblInfosSalle.setText("1h : 7€ / 2h : 10€");
				break;
			case GRANDE_SALLE:
				lblInfosSalle.setText("1h : 10€ / 2h : 16€");
				break;
			case SPECIFIQUE_SALLE:
				lblInfosSalle.setText("1h : 20€ / 2h : 30€");
				break;
			}
		}
		
		if(e.getSource() == btnRechercher) {
			Date d = new Date();
			try {
				d = new SimpleDateFormat("dd/MM/yyyy").parse(jtfDate.getText());
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			TRANCHE t = (TRANCHE) cbTrancheH.getSelectedItem();
			TYPE_SALLE ts = (TYPE_SALLE) cbTypeSalle.getSelectedItem();
			int duree = Integer.parseInt(jtfDuree.getText());
			try {
				PlageHoraire pl = CreerReservation.verifPlageLibre(d, t, ts, duree);
				
				if(pl!=null) {
					System.out.println("OK");
					System.out.println(pl.toString());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionPlageInexistante e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionClientInexistant e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionSalleInexistante e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionCreneauNonDisponible e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionJourFerie e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
