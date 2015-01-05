package presentation.reservation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelEffectuerReservation extends JPanel implements ActionListener {
	JFrame parent;
	CardLayout cl;
	JPanel panelReservation;

	String[] listContent = {"MANUEL", "AUTO", "MULTI"};
	
	JButton btnManuel;
	JButton btnAuto;
	JButton btnMulti;
	
	public PanelEffectuerReservation(JFrame parent) {
		this.parent = parent;
		
		// config du panel principal
		//setPreferredSize(new Dimension(500,500));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		// Partie de selection du type de réservation
		JPanel panelSelection = new JPanel(new GridLayout(1,3));
		btnManuel = new JButton("Manuelle");
		btnAuto = new JButton("Automatique");
		btnMulti = new JButton("Plusieurs jours");
		
		btnManuel.addActionListener(this);
		btnAuto.addActionListener(this);
		btnMulti.addActionListener(this);
		
		panelSelection.add(btnManuel);
		panelSelection.add(btnAuto);
		panelSelection.add(btnMulti);
		
		
		JPanel panelTypeResa = new JPanel();
		panelTypeResa.setLayout(new BoxLayout(panelTypeResa, BoxLayout.PAGE_AXIS));
		panelTypeResa.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Type de réservation"));
		panelTypeResa.add(panelSelection);
		
		panelTypeResa.setPreferredSize(new Dimension(500,50));
		
		// Gestion des différents panel des différents type de réservation
		panelReservation = new JPanel();
		cl = new CardLayout();
		
		panelReservation.setLayout(cl);
		
		JPanel panelResaManuel = new PanelResaManuel();
		JPanel panelResaAuto = new PanelResaAuto(parent);
		JPanel panelResaMulti = new PanelResaMulti();
		
		// On ajoute les différents panel de réservation
		panelReservation.add(panelResaManuel, listContent[0]);
		panelReservation.add(panelResaAuto, listContent[1]);
		panelReservation.add(panelResaMulti, listContent[2]);
		
		//panelReservation.setPreferredSize(new Dimension(500,250));
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(panelTypeResa, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(panelReservation,gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnManuel) {
			cl.show(panelReservation, listContent[0]);
		}
		
		if (e.getSource() == btnAuto) {
			cl.show(panelReservation, listContent[1]);
		}
		
		if (e.getSource() == btnMulti) {
			cl.show(panelReservation, listContent[2]);
		}
	}

}
