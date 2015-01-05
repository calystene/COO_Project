package presentation.client;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import metier.RechercheClient;
import data.Client;
import exception.ExceptionPlageInexistante;
import exception.ExceptionReservationInexistante;
import exception.ExceptionSalleInexistante;

@SuppressWarnings("serial")
public class TabRechercheClientReseservaionModel extends AbstractTableModel {
	
	String  title[] = {"Id_Reservation","Date_Limite_Reservation","Date_Reservation","Salle","H_Début","H_Fin","Prix","Supression","Etat_Confirmation"};
	private final Object[][] datas;
	
	public TabRechercheClientReseservaionModel(Client c) throws SQLException, ExceptionPlageInexistante, ExceptionSalleInexistante, ExceptionReservationInexistante {
		super();
		datas = RechercheClient.VisualiserReserClient(c);
	}
	
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
