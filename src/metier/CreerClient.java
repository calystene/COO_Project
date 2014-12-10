package metier;

import java.sql.SQLException;

import data.Client;

import exception.ExceptionClientExistant;
import factory.FactoryClient;

public class CreerClient {
	
	
	public CreerClient() {
	}
	
	public Client nouveauClient(String prenom, String nom, int numero) throws ExceptionClientExistant, SQLException {
		return FactoryClient.getInstance().creerClient(prenom, nom, numero);
	}
	
}
