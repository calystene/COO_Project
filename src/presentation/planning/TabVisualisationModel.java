package presentation.planning;

import java.sql.SQLException;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import metier.VisualiserPlanning;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;

@SuppressWarnings("serial")
public class TabVisualisationModel extends AbstractTableModel {
	private final String[] entetes = {"Nom salle","Date","Tranche","Heure début", "Heure fin", "Confirmée", "Client"};
	private final Object[][] datas;
	
	public TabVisualisationModel(TYPE_SALLE ts, Date d) throws SQLException, ExceptionPlageInexistante, ExceptionClientInexistant {
		super();
		datas = VisualiserPlanning.planningSalleDate(ts, d);
	}

	public TabVisualisationModel(TYPE_SALLE ts) throws SQLException, ExceptionPlageInexistante, ExceptionClientInexistant {
		super();
		datas = VisualiserPlanning.planningSalle(ts);
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return datas.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
	
	@Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datas[rowIndex][columnIndex];
    }
}
