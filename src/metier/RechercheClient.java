package metier;

import java.sql.SQLException;

import data.Client;
import exception.ExceptionClientInexistant;
import factory.FactoryClient;

public class RechercheClient {

	public static Client rechercherClient(String nom ,int numero) throws SQLException, ExceptionClientInexistant{
		return FactoryClient.getInstance().rechercherClient(nom, numero);
	}
}
