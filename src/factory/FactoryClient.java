 package factory;

import java.sql.ResultSet;
import java.util.HashMap;

import data.Client;
import exception.ExceptionClientExistant;

public class FactoryClient {
	private static FactoryClient singleton;
	
	private HashMap<Integer, Client> cacheClients;
	
	private FactoryClient() {
		cacheClients = new HashMap<Integer, Client>();
	}
	
	public static FactoryClient getInstance() {
		if(singleton==null) 
			singleton = new FactoryClient();
		
		return singleton;
	}
	
	
	public Client creerClient(String prenom, String nom, int num) throws ExceptionClientExistant {
		Client c = new Client(prenom, nom, num);
		int idClient = c.hashCode();
		String msgException = "Le client existe déjà";
		
		// On vérifie si le client est dans le cache
		if(cacheClients.containsKey(idClient))
			throw new ExceptionClientExistant(msgException);
		
		// On vérifie si le client existe en Base
		String sql = "SELECT COUNT(id_client) as id_client FROM CLIENT WHERE id_client=" + idClient;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		while(rs.next()) {
			if(rs.getInt("id_client")>=1) 
				throw new ExceptionClientExistant(msgException);
		}
		
		return null;
	}
	
}
