package metier;

import java.sql.SQLException;

import exception.ExceptionClientExistant;
import factory.FactoryClient;

public class CreerClient {
	public CreerClient(String prenom, String nom, int numero) throws ExceptionClientExistant, SQLException {
		
		FactoryClient.getInstance().creerClient(prenom, nom, numero);
		
	}
	
}
