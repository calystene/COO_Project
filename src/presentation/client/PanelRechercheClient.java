package presentation.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;

import presentation.planning.DateCellRenderer;
import presentation.planning.HeureCellRenderer;
import presentation.planning.NomCellRenderer;
import presentation.planning.StatutCellRenderer;
import metier.RechercheClient;
import data.Client;
import data.Reservation;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;
import exception.ExceptionReservationInexistante;
import exception.ExceptionSalleInexistante;
import factory.FactoryClient;
import factory.FactoryReservation;


@SuppressWarnings("serial")
public class PanelRechercheClient  extends JPanel implements ActionListener {
	JFrame parent;
	Client c;
	
	//Objet Panel1
	JPanel panel1 = new JPanel();
	
	JButton btnRechercher = new JButton("Rechercher");
	JButton btnEffacer = new JButton("Effacer");
	
	JLabel lblNom = new JLabel("Nom :");
	JLabel lblNumero = new JLabel("Numero :");

	JTextField jtfNom = new JTextField("Pierard");
	JTextField jtfNumero = new JTextField("637571940");

	//Objet Panel2
	JPanel panel2 = new JPanel();
	
	JLabel lblNom2 = new JLabel("Nom :");
	JLabel lblNumero2 = new JLabel("Téléphone :");
	JLabel lblPrenom2 = new JLabel("Prénom :");
	JLabel lblIdentifiant2 = new JLabel("Identifiant :");
	JLabel lblPointFid = new JLabel("Nbr points carte fidelité :");
	JLabel lblHeureDisp = new JLabel("Nbr heure carte fidelité :");
	JLabel lblNom2T = new JLabel("");
	JLabel lblNumero2T = new JLabel("");
	JLabel lblPrenom2T = new JLabel("");
	JLabel lblIdentifiant2T = new JLabel("");
	JLabel lblPointFidT = new JLabel("");
	JLabel lblHeureDispT = new JLabel("");

	JButton btnAfficherRes2 = new JButton("Afficher les réservations");
	JButton btnAfficherFor2 = new JButton("Afficher les forfaits");
	
	//Objet Panel3
	JPanel panel3 = new JPanel();
	JPanel panelTableauR = new JPanel();
	JPanel panelBouton =  new JPanel();
	JTable tableR = new JTable();
	JButton supprR = new JButton("Supprimer");
	JButton confR = new JButton("Confirmer");
	
	
	//Objet Panel4
	JPanel panel4 = new JPanel();
	JPanel panelTableauF = new JPanel();
	JTable tableF = new JTable();
	JButton creerForfait = new JButton("Créer un forfait");
	
	
	public PanelRechercheClient(JFrame pere) {
		parent = pere;
		
		//Panel de gauche
			JPanel panelG = new JPanel(new BorderLayout());
			panelG.setPreferredSize(new Dimension(300, 300));
			
			//Panel1
			panel1.setLayout(new GridLayout(3, 2));
			panel1.setPreferredSize(new Dimension(300, 100));
			
			panel1.add(lblNom);
			panel1.add(jtfNom);
			panel1.add(lblNumero);
			panel1.add(jtfNumero);		
			panel1.add(btnEffacer);
			panel1.add(btnRechercher);
			
			panel1.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.black), "Rechercher un client"));
			
			btnRechercher.addActionListener(this);
			btnEffacer.addActionListener(this);
			
			//Panel2
			panel2.setLayout(new GridLayout(7, 2));
			panel2.setPreferredSize(new Dimension(300, 200));
			panel2.setVisible(false);
			
			panel2.add(lblNom2);
			panel2.add(lblNom2T);
			panel2.add(lblPrenom2);
			panel2.add(lblPrenom2T);
			panel2.add(lblNumero2);
			panel2.add(lblNumero2T);
			panel2.add(lblIdentifiant2);
			panel2.add(lblIdentifiant2T);
			panel2.add(lblHeureDisp);
			panel2.add(lblHeureDispT);
			panel2.add(lblPointFid);
			panel2.add(lblPointFidT);
			panel2.add(btnAfficherRes2);		
			panel2.add(btnAfficherFor2);
			
			panel2.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.black), "Informations du client"));
			
			btnAfficherRes2.addActionListener(this);
			btnAfficherFor2.addActionListener(this);
			
			//Ajout au panel de gauche
			panelG.add(panel1, BorderLayout.NORTH);
			panelG.add(panel2, BorderLayout.CENTER);
		
		//Panel de droite
			JPanel panelD = new JPanel(new BorderLayout());
			panelD.setPreferredSize(new Dimension(800, 300));
	
			//Panel3
			panel3.setLayout(new BorderLayout());
			panel3.setPreferredSize(new Dimension(800, 300));
			panel3.setVisible(false);
			
			panelBouton.setLayout(new GridLayout(1, 2));
			panelBouton.setPreferredSize(new Dimension(800, 50));
			panelBouton.setVisible(true);
			
			panelBouton.add(supprR);
			panelBouton.add(confR);

			panel3.add(panelTableauR, BorderLayout.CENTER);
			panel3.add(panelBouton, BorderLayout.SOUTH);
	
			panel3.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.black), "Réservations du client"));
			panelBouton.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.black), "Sélectionner une ligne"));
			
			supprR.addActionListener(this);
			confR.addActionListener(this);
			
			//Panel4
			panel4.setLayout(new BorderLayout());
			panel4.setPreferredSize(new Dimension(800, 300));
			panel4.setVisible(false);
			
			panel4.add(panelTableauF, BorderLayout.CENTER);
			panel4.add(creerForfait, BorderLayout.SOUTH);
			
			panel4.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.black), "Forfaits du client"));
			
			creerForfait.addActionListener(this);
			
			//Ajout au panel de droite
			panelD.add(panel3, BorderLayout.NORTH);
			panelD.add(panel4, BorderLayout.SOUTH);
					
		//Ajout des panels gauche et droit � la frame principale
			setLayout(new BorderLayout());
			add(panelG, BorderLayout.WEST);
			add(panelD, BorderLayout.EAST);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnRechercher) {
			if (jtfNom.getText().length() != 0 && jtfNumero.getText().length() != 0) {
				if (jtfNumero.getText().length() == 9) {
					String nom = jtfNom.getText();
					int numero = Integer.parseInt(jtfNumero.getText());
					try {
						c =  RechercheClient.rechercherClient(nom, numero);
						lblNom2T.setText(c.getNom());
						lblPrenom2T.setText(c.getPrenom());
						lblNumero2T.setText("0"+Integer.toString(c.getNumero()));
						lblIdentifiant2T.setText(Integer.toString(c.hashCode()));
						lblHeureDispT.setText(Integer.toString(c.getCarteFidelite().getNbHeureGratuite()) + "h");
						lblPointFidT.setText(Integer.toString(c.getCarteFidelite().getNbPoint()) + " points");
						panel2.setVisible(true);
					} catch (ExceptionClientInexistant e1) {
						JOptionPane.showMessageDialog(parent,e1.getMessage(), "Le client n'existe pas",JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(parent,e2.getMessage(), "Le client n'existe pas",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(parent,"Le numéro doit faire 9 caractères");
				}
			} else {
				JOptionPane.showMessageDialog(parent,"Veuillez remplir tous les champs");
			}
		}
		
		if (e.getSource() == btnEffacer) {
			jtfNom.setText("");
			jtfNumero.setText("");
		}
		
		if (e.getSource() == btnAfficherRes2) {
			loadTableReservation();
		}
		
		if (e.getSource() == btnAfficherFor2) {
			loadTableForfait();
		}
		
		if (e.getSource() == creerForfait) {
			String nom = jtfNom.getText();
			int numero = Integer.parseInt(jtfNumero.getText());
			Client c;
			try {
				c = RechercheClient.rechercherClient(nom, numero);
				new FrameCreerForfait("Creer un forfait",c);
			} catch (SQLException e1) {
			} catch (ExceptionClientInexistant e1) {
			}
			
		}
		
		if (e.getSource() == supprR) {
			int i= tableR.getSelectedRow();
			int idReservation = (int) tableR.getValueAt(i,0);
			if (tableR.getValueAt(i,7) == "Non" | tableR.getValueAt(i,7) == "Hors-délais"){
			JOptionPane.showMessageDialog(parent, "Réservation supprimée!");
			RechercheClient.supprReservation(idReservation);
			
			// On retire les 5 points de fidélité gagné par le client lors de la réservation
			int nbPoints = c.getCarteFidelite().getNbPoint() -5;
			nbPoints = (nbPoints<0)?0:nbPoints;
			c.getCarteFidelite().setNbPoint(nbPoints);
			FactoryClient.getInstance().majClient(c);
			
			loadTableReservation();
			
			parent.revalidate();
			parent.repaint();
			} else {
				JOptionPane.showMessageDialog(parent, "Suppression impossible! Réservation deja confirmée");
			}
		}
		
		if (e.getSource() == confR) {
			int i= tableR.getSelectedRow();
			if (i==-1){
				JOptionPane.showMessageDialog(parent, "Aucune ligne selectionnée");
			} else {
			int idReservation = (int) tableR.getValueAt(i,0);
			if (tableR.getValueAt(i,7) == "Non" | tableR.getValueAt(i,7) == "Hors-délais"){
				
			//JOptionPane.showMessageDialog(parent, "Réservation confirmée!");
			Reservation r;
			try {
				r = RechercheClient.rechercheReservation(idReservation);
				new FrameConfimationReservation(parent,"Confirmer la réservation",r);
				
			//	RechercheClient.setEtatPaiementBDD(true, r);
			//	parent.revalidate();
			//	parent.repaint();
			} catch (SQLException | ExceptionReservationInexistante
					| ExceptionPlageInexistante | ExceptionClientInexistant
					| ExceptionSalleInexistante e1) {
				e1.printStackTrace();
			}

			} else {
				JOptionPane.showMessageDialog(parent, "Réservation deja confirmée");
			}
			}
		}
			
	}
	
	public void loadTableReservation() {
		try {
			tableR.removeAll();
		} catch (NullPointerException e2) {}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(750,220));
		
		//Parametre tableauR
		String nom = jtfNom.getText();
		int numero = Integer.parseInt(jtfNumero.getText());
		
		try {
			c = RechercheClient.rechercherClient(nom, numero);
			try {
				tableR = new JTable(new TabRechercheClientReseservaionModel(c));
			} catch (ExceptionReservationInexistante e1) {

			}
			tableR.setAutoCreateRowSorter(true);
			tableR.getTableHeader().setPreferredSize(new Dimension(750,30));
			tableR.getTableHeader().setReorderingAllowed(false);
			//tableR.setPreferredSize(new Dimension(750,250));
			
			tableR.getColumnModel().getColumn(0).setCellRenderer(new NomCellRenderer());
			tableR.getColumnModel().getColumn(1).setCellRenderer(new DateCellRenderer());
			tableR.getColumnModel().getColumn(2).setCellRenderer(new DateCellRenderer());
			tableR.getColumnModel().getColumn(3).setCellRenderer(new NomCellRenderer());
			tableR.getColumnModel().getColumn(4).setCellRenderer(new HeureCellRenderer());
			tableR.getColumnModel().getColumn(5).setCellRenderer(new HeureCellRenderer());
			tableR.getColumnModel().getColumn(6).setCellRenderer(new NomCellRenderer());
			tableR.getColumnModel().getColumn(7).setCellRenderer(new StatutCellRenderer());
			
			panelTableauR.removeAll();
			
			tableR.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scrollPane.setViewportView(tableR);
			panelTableauR.add(tableR.getTableHeader());
			panelTableauR.add(scrollPane);
			
			parent.revalidate();
			parent.repaint();
		} catch (ExceptionClientInexistant e1) {
			JOptionPane.showMessageDialog(parent,
					e1.getMessage(), "Le client n'existe pas",
					JOptionPane.WARNING_MESSAGE);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(parent,
					e2.getMessage(), "Le client n'existe pas",
					JOptionPane.ERROR_MESSAGE);
		} catch (ExceptionPlageInexistante e1) {

		} catch (ExceptionSalleInexistante e1) {

		}
	
		panel4.setVisible(false);
		panel3.setVisible(true);
	}
	
	
	
	public void loadTableForfait() {
		try {
			tableF.removeAll();
		} catch (NullPointerException e2) {}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(750,250));
		
		//Parametre tableauF
		String nom = jtfNom.getText();
		int numero = Integer.parseInt(jtfNumero.getText());
		Client c;
		try {
			c = RechercheClient.rechercherClient(nom, numero);
			tableF = new JTable(new TabRechercheClientForfaitModel(c));
			tableF.setAutoCreateRowSorter(true);
			tableF.getTableHeader().setPreferredSize(new Dimension(750,30));
			tableF.setPreferredSize(new Dimension(750,250));
			tableF.getTableHeader().setReorderingAllowed(false);
			
			tableF.getColumnModel().getColumn(0).setCellRenderer(new NomCellRenderer());
			tableF.getColumnModel().getColumn(3).setCellRenderer(new TypeForfaitCellRenderer());
		
			panelTableauF.removeAll();
			panelTableauF.add(tableF.getTableHeader());
			panelTableauF.add(scrollPane.add(tableF));
			
			parent.revalidate();
			parent.repaint();
		} catch (ExceptionClientInexistant e1) {
			JOptionPane.showMessageDialog(parent,
					e1.getMessage(), "Le client n'existe pas",
					JOptionPane.WARNING_MESSAGE);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(parent,
					e2.getMessage(), "Le client n'existe pas",
					JOptionPane.ERROR_MESSAGE);
		} catch (ExceptionPlageInexistante e1) {
		} catch (ExceptionSalleInexistante e1) {
		}
		
		panel3.setVisible(false);
		panel4.setVisible(true);
	}
}
