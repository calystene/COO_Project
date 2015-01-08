package presentation.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import metier.CreerForfait;
import data.Client;
import data.forfait.TYPE_FORFAIT;
import exception.ExceptionClientInexistant;
import exception.ExceptionForfaitExistant;
import exception.ExceptionForfaitInexistant;


@SuppressWarnings("serial")
public class PanelCreerForfait extends JPanel implements ActionListener {
	JFrame parent;
	Client client;
	
	//Objet Panel
	JPanel panel = new JPanel();
	
	JButton btn1 = new JButton("Forfait 12H Petite Salle");
	JButton btn2 = new JButton("Forfait 12H Grande Salle");
	JButton btn3 = new JButton("Forfait 24H Petite Salle");
	JButton btn4 = new JButton("Forfait 24H Grande Salle");

	public PanelCreerForfait(JFrame pere, Client c) {
		parent = pere;
		client = c;
		
		panel.setLayout(new GridLayout(1, 4));
		panel.setPreferredSize(new Dimension(900, 80));
		
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Choisir un forfait"));
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);	
		
		panel.setVisible(true);
		
		//Ajout du panel � la frame principale
		setLayout(new BorderLayout());
		add(panel);
	}
	
	public void actionPerformed(ActionEvent e) {
		TYPE_FORFAIT t;
		if (e.getSource() == btn1 || e.getSource() == btn2 || e.getSource() == btn3 || e.getSource() == btn4 ) {
			if (e.getSource() == btn1){
				t = TYPE_FORFAIT.A_PETITE;
			} else if (e.getSource() == btn2) {
					t = TYPE_FORFAIT.A_GRANDE;
				} else if (e.getSource() == btn3) {
						t = TYPE_FORFAIT.B_PETITE;
					} else {
							t = TYPE_FORFAIT.B_GRANDE;
					}
			try {

				CreerForfait.CreerForfaitClient(client, t);
				JOptionPane.showMessageDialog(parent, "Création réussie! Merci d'avoir choisi ce forfait");
			} catch (ExceptionForfaitExistant e1) {
				JOptionPane.showMessageDialog(parent, "Echec de création! Le client posséde déja ce forfait");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(parent, "Erreur creation forfait");
			} 
		}
	}
}
