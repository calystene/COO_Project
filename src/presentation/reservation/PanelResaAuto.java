package presentation.reservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.date.DateManager;
import metier.CreerClient;
import metier.CreerReservation;
import metier.RechercheClient;
import data.Client;
import data.Reservation;
import data.horaire.PlageHoraire;
import data.horaire.TRANCHE;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientExistant;
import exception.ExceptionClientInexistant;
import exception.ExceptionCreneauNonDisponible;
import exception.ExceptionJourFerie;
import exception.ExceptionPlageInexistante;
import exception.ExceptionReservationExistante;
import exception.ExceptionSalleInexistante;

@SuppressWarnings("serial")
public class PanelResaAuto extends JPanel implements ActionListener {
	JFrame parent;

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

	public PanelResaAuto(JFrame parent) {
		this.parent = parent;

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
		lblInfoDate.setPreferredSize(new Dimension(150, 25));

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

		TYPE_SALLE[] salle = { TYPE_SALLE.PETITE_SALLE,
				TYPE_SALLE.GRANDE_SALLE, TYPE_SALLE.SPECIFIQUE_SALLE };
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
		if (e.getSource() == cbTrancheH) {
			switch ((TRANCHE) cbTrancheH.getSelectedItem()) {
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

		if (e.getSource() == cbTypeSalle) {
			switch ((TYPE_SALLE) cbTypeSalle.getSelectedItem()) {
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

		if (e.getSource() == btnRechercher) {
			Date d = new Date();
			PlageHoraire ph = null;
			Client c = null;
			TRANCHE t = null;
			TYPE_SALLE ts = null;
			int duree = 0;
			Reservation r;
			
			// Recherche du créneau de libre
			try {
				// On récupère les saisies
				d = new SimpleDateFormat("dd-MM-yyyy").parse(jtfDate.getText());
				t = (TRANCHE) cbTrancheH.getSelectedItem();
				ts = (TYPE_SALLE) cbTypeSalle.getSelectedItem();
				duree = Integer.parseInt(jtfDuree.getText());

				ph = CreerReservation.verifPlageLibre(d, t, ts,
						duree);

			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ExceptionPlageInexistante e1) {
				e1.printStackTrace();
			} catch (ExceptionClientInexistant e1) {
				e1.printStackTrace();
			} catch (ExceptionSalleInexistante e1) {
				e1.printStackTrace();
			} catch (ExceptionCreneauNonDisponible e1) {
				JOptionPane.showMessageDialog(parent,
						"Le créneau horaire choisi n'est pas disponible",
						"Non disponible", JOptionPane.WARNING_MESSAGE);
			} catch (ExceptionJourFerie e1) {
				JOptionPane.showMessageDialog(parent,
						"La date choisie est un jour férié",
						"Jour férié", JOptionPane.WARNING_MESSAGE);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(parent,
						"Le format de la date n'est pas correct",
						"Erreur parsing date", JOptionPane.WARNING_MESSAGE);
			}
			
			if (ph != null) {
				Object[] options = {"Oui", "Non"};
				int choice = JOptionPane.showOptionDialog(parent,
						"Le créneaux horaire " + ph + " est disponible. Souhaitez-vous le réserver ?",
						"Créneau Disponible", 
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						null,
						options,
						options[0]);
				
				// Récupération du choix
				if(choice==0) {
					String nom = JOptionPane.showInputDialog(parent,
							"Saisir le nom du client :",
							"Nom du client",
							JOptionPane.QUESTION_MESSAGE);
					int numero = Integer.parseInt(JOptionPane.showInputDialog(parent,
							"Saisir le numéro du client :",
							"Numéro du client",
							JOptionPane.QUESTION_MESSAGE));
					
					if(nom.length()!=0 && numero!=0) {
					
						// Récupération de la donnée client
						try {
							// On recherche le client en base, s'il n'existe pas on le crée
							c = RechercheClient.rechercherClient(nom, numero);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(parent,
									   e1.getMessage(),
									    "Erreur création",
									    JOptionPane.ERROR_MESSAGE);
						} catch (ExceptionClientInexistant e1) {
							try {
								// Si client inexistant alors on le crée
								c = CreerClient.nouveauClient(nom, numero);
							} catch (ExceptionClientExistant e2) {
								JOptionPane.showMessageDialog(parent,
										   e1.getMessage(),
										    "Erreur création",
										    JOptionPane.WARNING_MESSAGE);
							} catch (SQLException e2) {
								JOptionPane.showMessageDialog(parent,
										   e1.getMessage(),
										    "Erreur création",
										    JOptionPane.ERROR_MESSAGE);
							}
						}
						
						try {
							// Création de la réservation
							r = CreerReservation.creerReservation(d, ph, c.getNumero(), c.getNom(), ts, duree);
							
							// JOptionPane affichant le résumé de la réservation
							JOptionPane.showMessageDialog(parent,
									   "Résumé de la réservation :\n" +
											   "Client : " + r.getClient() + "\n" +
											   "Date : " + DateManager.valueOf(r.getDateReservation()) + "\n" +
											   "Date max confirmation : " + DateManager.valueOf(DateManager.addOneWeekFromDate(r.getDatePriseReservation())) + "\n" +
											   "Plage horaire : " + r.getPlage() + "\n" +
											   "Prix : " + r.getPrix() + "€\n" +
											   "Salle : " + r.getSalle(),
									    "Réservation réussie",
									    JOptionPane.INFORMATION_MESSAGE);
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(parent,
									   e1.getMessage(),
									    "Erreur création",
									    JOptionPane.ERROR_MESSAGE);
						} catch (ExceptionClientInexistant e1) {
							JOptionPane.showMessageDialog(parent,
									   e1.getMessage(),
									    "Erreur création",
									    JOptionPane.WARNING_MESSAGE);
						} catch (ExceptionPlageInexistante e1) {
							JOptionPane.showMessageDialog(parent,
									   e1.getMessage(),
									    "Erreur création",
									    JOptionPane.WARNING_MESSAGE);
						} catch (ExceptionSalleInexistante e1) {
							JOptionPane.showMessageDialog(parent,
									   e1.getMessage(),
									    "Erreur création",
									    JOptionPane.WARNING_MESSAGE);
						} catch (ExceptionCreneauNonDisponible e1) {
							JOptionPane.showMessageDialog(parent,
									   e1.getMessage(),
									    "Erreur création",
									    JOptionPane.WARNING_MESSAGE);
						} catch (ExceptionReservationExistante e1) {
							JOptionPane.showMessageDialog(parent,
									   e1.getMessage(),
									    "Erreur création",
									    JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		}

	}
}
