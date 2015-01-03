package presentation.planning;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class HeureCellRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
		
		String heure = String.valueOf(value) + "h";
		setText(heure);
		setFont(this.getFont().deriveFont(Font.BOLD));
		setHorizontalAlignment(JLabel.CENTER); 
		
		return this;
	}
}
