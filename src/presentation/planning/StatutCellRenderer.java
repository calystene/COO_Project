package presentation.planning;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class StatutCellRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);

		boolean etat = (boolean) value;
		
		if(etat) {
			setText("Oui");
			setBackground(Color.GREEN);
		} else {
			setText("Non");
			setBackground(Color.RED);
		}
		setFont(this.getFont().deriveFont(Font.BOLD));
		setHorizontalAlignment(JLabel.CENTER); 
		
		return this;
	}
}
