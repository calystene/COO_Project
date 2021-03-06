import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import data.Client;
import data.forfait.Forfait;
import data.forfait.TYPE_FORFAIT;
import exception.ExceptionClientExistant;
import exception.ExceptionClientInexistant;
import exception.ExceptionForfaitExistant;
import factory.FactoryClient;
import factory.FactoryForfait;
import factory.FactorySQL;
import util.date.DateManager;


public class Main {

	public static void main(String[] args) {
		Date date = DateManager.getDate();
		Date dateSQL = DateManager.getDateSQL();
		
//		System.out.println(DateManager.dateToString());
//		System.out.println(DateManager.valueOf(dateSQL));
//		System.out.println(DateManager.valueOf(DateManager.addMonthFromToday(5)));
//		System.out.println(DateManager.valueOf(DateManager.addMonthFromDate(dateSQL,5)));
//	
//		GregorianCalendar paque = new GregorianCalendar(2015,3,6,12,30);
//		GregorianCalendar gc = new GregorianCalendar(2015,0,1,9,30);
//		
//		System.out.println(DateManager.jourOuvrable(gc.getTime()));
//		System.out.println(DateManager.jourOuvrable(paque.getTime()));
//		System.out.println(DateManager.jourOuvrable(date));
		
		Client c = null;
		try {
			//FactoryClient.getInstance().creerClient("Thomas", "Pierard", 637571940);
			
			c = FactoryClient.getInstance().rechercherClient("Pierre",657371940);
			
			ArrayList<Client> liste = FactoryClient.getInstance().listeClient();
			
//			for(Client cli : liste) {
//				System.out.println(cli.getPrenom() + " " + cli.getNom());
//			}
			System.out.println(c.getNom()  + " " + c.getNumero());
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
		
		ArrayList<Forfait> listeF;
		try {
			listeF = FactoryForfait.getInstance().listeTypeForfait();
			
			Forfait forf = FactoryForfait.getInstance().rechercherForfait(2046125353);
			
			for(Forfait forfait : listeF) {
				System.out.println(forfait.getLibelle() + "  " + forfait.getPrix() + " " + forfait.getHeureDisponible());
			}
			
			System.out.println("--- Cli : " + forf.getClient().getNom() + "; Forfait :" + forf.getLibelle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		FactorySQL.getInstance().shutdown();
	}

}
