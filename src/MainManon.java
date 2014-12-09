import java.sql.SQLException;

import data.horaire.TRANCHE;
import exception.ExceptionPlageInexistante;
import factory.FactoryPlageHoraire;
import factory.FactoryReservation;
import factory.FactorySQL;

public class MainManon {


	public static void main(String[] args) throws SQLException, ExceptionPlageInexistante{

		FactoryPlageHoraire.getInstance().creerPlageHoraire (10, 11, TRANCHE.MATIN);

	//	for (Salle s : FactorySalle.getInstance().listeSalleBDD() ){
	//		System.out.println(s);	
	//	}

			
			
			FactorySQL.getInstance().shutdown();
	}
	
}