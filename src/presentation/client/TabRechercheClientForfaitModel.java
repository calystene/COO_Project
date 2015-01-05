package presentation.client;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import metier.RechercheClient;
import data.Client;
import exception.ExceptionPlageInexistante;
import exception.ExceptionSalleInexistante;

@SuppressWarnings("serial")
public class TabRechercheClientForfaitModel extends AbstractTableModel {
	
	String  title[] = {"Id Forfait","Nbr d'heures disponibles","Date de fin de validité","Type de Forfait"};
	private final Object[][] datas;
	
	public TabRechercheClientForfaitModel(Client c) throws SQLException, ExceptionPlageInexistante, ExceptionSalleInexistante {
		super();
		datas = RechercheClient.VisualiserForfaitClient(c);
	}
	public boolean isCellEditable(int row, int colunm){ 
        if (colunm ==1 ) 
            return true; 
        else 
            return false; 
     }; 
	
	@Override
	public int getColumnCount() {
		return title.length;
	}

	@Override
	public int getRowCount() {
		return datas.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
        return title[columnIndex];
    }
	
	@Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datas[rowIndex][columnIndex];
    }
}
