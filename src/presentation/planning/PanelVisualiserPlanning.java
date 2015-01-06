package presentation.planning;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import data.salle.TYPE_SALLE;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;

@SuppressWarnings("serial")
public class PanelVisualiserPlanning extends JPanel implements ActionListener {
	JFrame parent;
	GridBagConstraints gbc;
	
	JComboBox<TYPE_SALLE> cbTypeSalle;
	JTextField jtfDate;
	JButton btnVisualiser;
	
	JPanel panelTableau;
	JTable table;
	JScrollPane scrollPane;
	
	public PanelVisualiserPlanning(JFrame parent) {
		this.parent = parent;
		setPreferredSize(new Dimension(630,500));
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		// Config de la partie haute de la fenêtre (Paramètres visualisation)
		JPanel panelParam = new JPanel();
		panelParam.setLayout(new GridLayout(1,3,5,2));
		panelParam.setPreferredSize(new Dimension(610, 50));
		
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
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(panelParam, gbc);
		
		
		
		// Partie basse de la fenêtre
		panelTableau = new JPanel();
		panelTableau.setPreferredSize(new Dimension(610,440));
		panelTableau.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black), "Planning"));
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(panelTableau,gbc);
		loadTabSalle();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVisualiser) {
			if(!(jtfDate.getText().length()==0)) {
				loadTabSalleDate();
			} else {
				loadTabSalle();
			}
		}
	}
	
	
	/**
	 * Charge le tableau lorsqu'on fournit juste un type de salle 
	 */
	private void loadTabSalle() {
		try {
			TYPE_SALLE ts = (TYPE_SALLE) cbTypeSalle.getSelectedItem();
			
			try {
				table.removeAll();
			} catch (NullPointerException e2) {}
			
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(600,410));
			scrollPane.setMaximumSize(new Dimension(600,410));
			
			// Paramétrage du tableau
			table = new JTable(new TabVisualisationModel(ts));
			table.setAutoCreateRowSorter(true);
			table.getTableHeader().setPreferredSize(new Dimension(600,30));
			
			//table.setPreferredSize(new Dimension(680,0));
			table.getColumnModel().getColumn(0).setCellRenderer(new NomCellRenderer());
			table.getColumnModel().getColumn(1).setCellRenderer(new DateCellRenderer());
			table.getColumnModel().getColumn(2).setCellRenderer(new TrancheCellRenderer());
			table.getColumnModel().getColumn(3).setCellRenderer(new HeureCellRenderer());
			table.getColumnModel().getColumn(4).setCellRenderer(new HeureCellRenderer());
			table.getColumnModel().getColumn(5).setCellRenderer(new StatutCellRenderer());
			table.getColumnModel().getColumn(6).setCellRenderer(new NomCellRenderer());
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
			
			panelTableau.removeAll();

			scrollPane.setViewportView(table);

			panelTableau.add(table.getTableHeader());
			panelTableau.add(scrollPane);
			
			parent.revalidate();
			parent.repaint();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ExceptionPlageInexistante e1) {
			e1.printStackTrace();
		} catch (ExceptionClientInexistant e1) {
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * Charge le tableau lorsqu'on fournit une date et un type de salle
	 */
	private void loadTabSalleDate () {
		try {
			Date d = new SimpleDateFormat("dd-MM-yyyy").parse(jtfDate.getText());
			TYPE_SALLE ts = (TYPE_SALLE) cbTypeSalle.getSelectedItem();
			
			try {
				table.removeAll();
			} catch (NullPointerException e2) {}
			
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(600,410));
			scrollPane.setMaximumSize(new Dimension(600,410));
			
			// Paramétrage du tableau
			table = new JTable(new TabVisualisationModel(ts,d));
			table.setAutoCreateRowSorter(true);
			table.getTableHeader().setPreferredSize(new Dimension(600,30));
			
			//table.setPreferredSize(new Dimension(680,0));
			table.getColumnModel().getColumn(0).setCellRenderer(new NomCellRenderer());
			table.getColumnModel().getColumn(1).setCellRenderer(new DateCellRenderer());
			table.getColumnModel().getColumn(2).setCellRenderer(new TrancheCellRenderer());
			table.getColumnModel().getColumn(3).setCellRenderer(new HeureCellRenderer());
			table.getColumnModel().getColumn(4).setCellRenderer(new HeureCellRenderer());
			table.getColumnModel().getColumn(5).setCellRenderer(new StatutCellRenderer());
			table.getColumnModel().getColumn(6).setCellRenderer(new NomCellRenderer());
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
			
			panelTableau.removeAll();

			scrollPane.setViewportView(table);

			panelTableau.add(table.getTableHeader());
			panelTableau.add(scrollPane);
			
			parent.revalidate();
			parent.repaint();
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(parent,
					"Utilisé le format de date suivant : JJ-MM-AAAA",
					"Erreur parsing date", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ExceptionPlageInexistante e1) {
			e1.printStackTrace();
		} catch (ExceptionClientInexistant e1) {
			e1.printStackTrace();
		}
	}
	
}
