import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

		Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("04/02/2015");
		Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2015");
		PlageHoraire h = FactoryPlageHoraire.getInstance().creerPlageHoraire(9, 11, TRANCHE.MATIN);
		Client c = FactoryClient.getInstance().rechercherClient("barrois", 220102585);
		Salle s = FactorySalle.getInstance().rechercheSalle("pink");
		
		
		//FactoryReservation.getInstance().creerReservation(DateManager.dateToSQL(d1), DateManager.dateToSQL(d2), h, 20 ,c, s, 2);
		//FactoryReservation.getInstance(). supprReservation(175590558);
		FactoryReservation.getInstance(). rechercheReservation(175590558);  //NE PASSE PAS 
		
	//	for (Salle s : FactorySalle.getInstance().listeSalleBDD() ){
	//		System.out.println(s);	
	//	}

			
			
			FactorySQL.getInstance().shutdown();
	}
	
}