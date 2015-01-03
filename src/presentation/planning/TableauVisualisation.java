package presentation.planning;

import javax.swing.table.AbstractTableModel;

public class TableauVisualisation extends AbstractTableModel {
	private final String[] entete = {"Nom salle","Heure début", "Heure Fin", "Statut"};
	private Object[][] datas;
	
	public TableauVisualisation() {
		super();
		
	}

	@Override
	public int getColumnCount() {
		return entete.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datas.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
        return entete[columnIndex];
    }
	
	@Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datas[rowIndex][columnIndex];
    }
}
