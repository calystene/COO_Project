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

import presentation.planning.NomCellRenderer;
import metier.RechercheClient;
import data.Client;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;
import exception.ExceptionReservationInexistante;
import exception.ExceptionSalleInexistante;
import factory.FactoryClient;
import factory.FactoryReservation;


@SuppressWarnings("serial")
public class PanelRechercheClient  extends JPanel implements ActionListener {
	JFrame parent;
	
	//Objet Panel1
	JPanel panel1 = new JPanel();
	
	JButton btnRechercher = new JButton("Rechercher");
	JButton btnEffacer = new JButton("Effacer");
	
	JLabel lblNom = new JLabel("Nom :");
	JLabel lblNumero = new JLabel("Numero :");

	JTextField jtfNom = new JTextField("barrois");
	JTextField jtfNumero = new JTextField("642203927");

	//Objet Panel2
	JPanel panel2 = new JPanel();
	
	JLabel lblNom2 = new JLabel("Nom :");
	JLabel lblNumero2 = new JLabel("Téléphone :");
	JLabel lblPrenom2 = new JLabel("Prénom :");
	JLabel lblIdentifiant2 = new JLabel("Identifiant :");
	JLabel lblNom2T = new JLabel("");
	JLabel lblNumero2T = new JLabel("");
	JLabel lblPrenom2T = new JLabel("");
	JLabel lblIdentifiant2T = new JLabel("");

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
			panel2.setLayout(new GridLayout(5, 2));
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
					
		//Ajout des panels gauche et droit à la frame principale
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
						Client c =  RechercheClient.rechercherClient(nom, numero);
						lblNom2T.setText(c.getNom());
						lblPrenom2T.setText(c.getPrenom());
						lblNumero2T.setText("0"+Integer.toString(c.getNumero()));
						lblIdentifiant2T.setText(Integer.toString(c.hashCode()));
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
			jtfNom.setText(" ");
			jtfNumero.setText(" ");
		}
		
		if (e.getSource() == btnAfficherRes2) {
			try {
				tableR.removeAll();
			} catch (NullPointerException e2) {}
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(750,250));
			
			//Parametre tableauR
			String nom = jtfNom.getText();
			int numero = Integer.parseInt(jtfNumero.getText());
			Client c;
			try {
				c = RechercheClient.rechercherClient(nom, numero);
				try {
					tableR = new JTable(new TabRechercheClientReseservaionModel(c));
				} catch (ExceptionReservationInexistante e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tableR.setAutoCreateRowSorter(true);
				tableR.getTableHeader().setPreferredSize(new Dimension(750,30));
				tableR.setPreferredSize(new Dimension(750,250));
				
				tableR.getColumnModel().getColumn(0).setCellRenderer(new NomCellRenderer());
				//tableR.getColumnModel().getColumn(7).setCellRenderer(new BoutonCellRenderer());
				//tableR.getColumnModel().getColumn(8).setCellRenderer(new BoutonCellRenderer());
				//tableR.getColumnModel().getColumn(8).setCellRenderer(new BoutonCellRenderer());
				
				//tableR.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
				//tableR.getColumnModel().getColumn(8).setCellEditor((TableCellEditor) new ButtonEditor(new JCheckBox()));
				
				panelTableauR.removeAll();
				panelTableauR.add(tableR.getTableHeader());
				panelTableauR.add(scrollPane.add(tableR));
				
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionSalleInexistante e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			panel4.setVisible(false);
			panel3.setVisible(true);
		}
		
		if (e.getSource() == btnAfficherFor2) {
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionSalleInexistante e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			panel3.setVisible(false);
			panel4.setVisible(true);
		}
		
		if (e.getSource() == creerForfait) {
			String nom = jtfNom.getText();
			int numero = Integer.parseInt(jtfNumero.getText());
			Client c;
			try {
				c = RechercheClient.rechercherClient(nom, numero);
				new FrameCreerForfait("Creer un forfait",c);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionClientInexistant e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
		if (e.getSource() == supprR) {
			int i= tableR.getSelectedRow();
			int idReservation = (int) tableR.getValueAt(i,0);
			if (tableR.getValueAt(i,7) == "Possible"){
			JOptionPane.showMessageDialog(parent, idReservation);
			RechercheClient.supprReservation(idReservation);
			} else {
				JOptionPane.showMessageDialog(parent, "Suppression impossible! Réservation deja confirmée");
			}
		}
			
	}
	

}
