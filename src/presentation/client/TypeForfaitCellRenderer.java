package presentation.client;

import java.awt.Component;


import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


import data.forfait.TYPE_FORFAIT;

@SuppressWarnings("serial")
public class TypeForfaitCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		TYPE_FORFAIT etat = (TYPE_FORFAIT) value;
		
		if(etat==TYPE_FORFAIT.A_GRANDE) {
			setText("Forfait 12h Grande Salle");
		} else if (etat==TYPE_FORFAIT.B_GRANDE) {
			setText("Forfait 24h Grande Salle");
		} else if (etat==TYPE_FORFAIT.A_PETITE) {
			setText("Forfait 12h Petite Salle");
		} else {
			setText("Forfait 24h Petite Salle");
		}
		setHorizontalAlignment(JLabel.CENTER); 
		
		return this;
	}

}