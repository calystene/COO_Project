import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import data.Client;
import exception.ExceptionClientExistant;
import factory.FactoryClient;
import factory.FactorySQL;
import util.date.DateManager;


public class Main {

	public static void main(String[] args) {
		Date date = DateManager.getInstance().getDate();
		Date dateSQL = DateManager.getInstance().getDateSQL();
		
		System.out.println(DateManager.getInstance().dateToString());
		System.out.println(DateManager.getInstance().valueOf(dateSQL));
		System.out.println(DateManager.getInstance().valueOf(DateManager.getInstance().addMonth(5)));
		System.out.println(DateManager.getInstance().valueOf(DateManager.getInstance().addMonth(dateSQL,5)));
	
		GregorianCalendar paque = new GregorianCalendar(2015,3,6,12,30);
		GregorianCalendar gc = new GregorianCalendar(2015,0,1,9,30);
		
		System.out.println(DateManager.getInstance().jourOuvrable(gc.getTime()));
		System.out.println(DateManager.getInstance().jourOuvrable(paque.getTime()));
		System.out.println(DateManager.getInstance().jourOuvrable(date));
		
		
		try {
			Client c = FactoryClient.getInstance().creerClient("Jean", "Pierre", 657274020);
			
			ResultSet rs = FactorySQL.getInstance().getResultSet("SELECT * FROM CLIENT");
			
			while(rs.next()) {
				System.out.println(rs.getString("nom") + " " + rs.getString("prenom"));
			}
			
			ArrayList<Client> liste = FactoryClient.getInstance().listeClient();
			
			for(Client cli : liste) {
				System.out.println(cli.getPrenom() + " " + cli.getNom());
			}
			
		} catch (ExceptionClientExistant e) {
			String msg = e.getMessage();
			System.out.println(msg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FactorySQL.getInstance().shutdown();
	}

}
