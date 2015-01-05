package presentation.planning;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class StatutCellRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);

		String etat = (String) value;
		
		if(etat.equals("Oui")) {
			setBackground(Color.GREEN);
		} else if(etat.equals("Non")) {
			setBackground(Color.YELLOW);
		} else if(etat.equals("Hors-délais")) {
			setBackground(Color.RED);
		}
		setFont(this.getFont().deriveFont(Font.BOLD));
		setHorizontalAlignment(JLabel.CENTER); 
		
		return this;
	}
}
