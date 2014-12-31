package metier;

import java.sql.SQLException;

import data.Client;
import exception.ExceptionClientInexistant;
import factory.FactoryClient;

public class RechercheClient {

	public static Client rechercherClient(String nom ,int numero) throws SQLException, ExceptionClientInexistant{
		return FactoryClient.getInstance().rechercherClient(nom, numero);
	}
	
	public static void ajouterPointFidelite(String nom, int numero, int points) throws SQLException, ExceptionClientInexistant {
		Client c = FactoryClient.getInstance().rechercherClient(nom, numero);
		
		int nbPoints = c.getCarteFidelite().getNbPoint();
		
		nbPoints += points;
		
		if(nbPoints == 150) {
			c.getCarteFidelite().setNbPoint(0);
				
			int nbHeureGratuite = c.getCarteFidelite().getNbHeureGratuite();
			nbHeureGratuite += 1;
			c.getCarteFidelite().setNbHeureGratuite(nbHeureGratuite);
		}
		
		FactoryClient.getInstance().majClient(c);
	}
}
