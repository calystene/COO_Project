import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hsqldb.DatabaseManager;

import util.date.DateManager;
import data.Client;
import data.Reservation;
import data.horaire.PlageHoraire;
import data.horaire.TRANCHE;
import data.salle.Salle;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientExistant;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;
import exception.ExceptionReservationExistante;
import exception.ExceptionReservationInexistante;
import exception.ExceptionSalleExistante;
import exception.ExceptionSalleInexistante;
import factory.FactoryClient;
import factory.FactoryPlageHoraire;
import factory.FactoryReservation;
import factory.FactorySQL;
import factory.FactorySalle;

public class MainManon {


	public static void main(String[] args) throws SQLException, ExceptionPlageInexistante, ParseException, ExceptionClientExistant, ExceptionSalleExistante, ExceptionReservationExistante, ExceptionClientInexistant, ExceptionSalleInexistante, ExceptionReservationInexistante{

		Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2014");
		Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2014");
		PlageHoraire h = FactoryPlageHoraire.getInstance().creerPlageHoraire(8, 10, TRANCHE.MATIN);
		Client c = FactoryClient.getInstance().rechercherClient("Pierard", 637571940);
		Salle s = FactorySalle.getInstance().rechercheSalle("pink");
		
		//FactoryReservation.getInstance().creerReservation(DateManager.dateToSQL(d1), DateManager.dateToSQL(d2), h, 20 ,c, s, 2);
		
		h = FactoryPlageHoraire.getInstance().creerPlageHoraire(13, 15, TRANCHE.AM);
		//FactoryReservation.getInstance().creerReservation(DateManager.dateToSQL(d1), DateManager.dateToSQL(d2), h, 20 ,c, s, 2);
		
		ArrayList<Reservation> liste = new ArrayList<Reservation>();
		
		liste = FactoryReservation.getInstance().listeReservationDate(DateManager.dateToSQL(DateManager.getDate()));
		
		for(Reservation r : liste) {
			System.out.println(r.toString());
			System.out.println(r.getClient().toString());
		}
		
		//FactoryReservation.getInstance(). supprReservation(175590558);
		//Reservation reser = FactoryReservation.getInstance(). rechercheReservation(d2, c); 
		
		//System.out.println(reser);
		
//		ArrayList<Reservation> liste = c.getReservation();
//		
//		for(Reservation r : liste) {
//			System.out.println(r);
//		}
		
	//	for (Salle s : FactorySalle.getInstance().listeSalleBDD() ){
	//		System.out.println(s);	
	//	}

			
			
			FactorySQL.getInstance().shutdown();
	}
	
}