package presentation.planning;

import java.awt.Component;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import util.date.DateManager;

@SuppressWarnings("serial")
public class DateCellRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
		
		Date date = (Date) value;
		setText(DateManager.valueOf(date));
		setFont(this.getFont().deriveFont(Font.BOLD));
		setHorizontalAlignment(JLabel.CENTER); 
		
		return this;
	}
}
