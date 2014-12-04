import java.sql.SQLException;

import data.salle.Salle;
import data.salle.TYPE_SALLE;
import exception.ExceptionSalleExistante;
import factory.FactorySQL;
import factory.FactorySalle;


public class MainManon {


	public static void main(String[] args) throws ExceptionSalleExistante, SQLException  {
		
		FactorySalle.getInstance().supprSalle("Korn");

		for (Salle s : FactorySalle.getInstance().listeSalleBDD() ){
			System.out.println(s);	
		}

			
			
			FactorySQL.getInstance().shutdown();
	}
	
}
