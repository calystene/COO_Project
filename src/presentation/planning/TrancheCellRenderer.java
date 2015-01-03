package presentation.planning;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import data.horaire.TRANCHE;

public class TrancheCellRenderer extends DefaultTableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
		
		TRANCHE tranche = (TRANCHE) value;
		
		switch(tranche) {
		case MATIN:
			setText("Matin");
			break;
		case AM:
			setText("Après-Midi");
			break;
		case SOIR:
			setText("Soir");
			break;
		}
		
		setFont(this.getFont().deriveFont(Font.BOLD));
		setHorizontalAlignment(JLabel.CENTER); 
		return this;
	}
}
