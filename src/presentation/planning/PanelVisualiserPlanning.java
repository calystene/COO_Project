package presentation.planning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import data.horaire.TRANCHE;
import data.salle.TYPE_SALLE;

public class PanelVisualiserPlanning extends JPanel implements ActionListener {
	JFrame parent;
	
	JComboBox cbTypeSalle;
	JTextField jtfDate;
	JButton btnVisualiser;
	
	JPanel panelTableau;
	JTable table;
	
	public PanelVisualiserPlanning(JFrame parent) {
		this.parent = parent;
		setPreferredSize(new Dimension(600,500));
		
		// Config de la partie haute de la fenêtre (Paramètres visualisation)
		JPanel panelParam = new JPanel();
		panelParam.setLayout(new GridLayout(1,3,5,2));
		
		TYPE_SALLE[] tabTypeSalle = {TYPE_SALLE.PETITE_SALLE, TYPE_SALLE.GRANDE_SALLE, TYPE_SALLE.SPECIFIQUE_SALLE};
		cbTypeSalle = new JComboBox<TYPE_SALLE>(tabTypeSalle);
		
		jtfDate = new JTextField();
		
		btnVisualiser = new JButton("Visualiser");
		btnVisualiser.addActionListener(this);
		
		panelParam.add(cbTypeSalle);
		panelParam.add(jtfDate);
		panelParam.add(btnVisualiser);
		
		panelParam.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Paramètres de visualisation"));
		
		add(panelParam, BorderLayout.NORTH);
		
		// Partie baisse de la fenêtre
					panelTableau = new JPanel();
					panelTableau.setPreferredSize(new Dimension(550,450));

					add(panelTableau,BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVisualiser) {
			
//			Object[][] datas = {
//					{"pink",9,11, "Non confirmé"},
//					{"pink",11,13, "Confirmé"}
//			};
			table = new JTable(new TableauVisualisation());
			
			panelTableau.add(table);
		}
		
	}
	
}
