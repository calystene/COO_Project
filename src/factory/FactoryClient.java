package factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.CarteFidelite;
import data.Client;
import exception.ExceptionClientExistant;
import exception.ExceptionClientInexistant;

public class FactoryClient {
	private static FactoryClient singleton;

	private HashMap<Integer, Client> cacheClients;

	private FactoryClient() {
		cacheClients = new HashMap<Integer, Client>();
	}

	public static FactoryClient getInstance() {
		if (singleton == null)
			singleton = new FactoryClient();

		return singleton;
	}

	
	
	
	/**
	 * Crée un nouveau Client et l'ajoute au cache et à la BDD
	 * 
	 * @param prenom
	 * @param nom
	 * @param num
	 * @return le client créée
	 * @throws ExceptionClientExistant
	 * @throws SQLException
	 */
	public Client creerClient(String prenom, String nom, int num)
			throws ExceptionClientExistant, SQLException {

		Client c = new Client(prenom, nom, num);
		int idClient = c.hashCode();
		c.setCarteFidelite(new CarteFidelite(c));

		String msgException = "Le client existe déjà";

		// On vérifie si le client est dans le cache
		if (cacheClients.containsKey(idClient))
			throw new ExceptionClientExistant(msgException);

		// On vérifie si le client existe en Base
		String sql = "SELECT COUNT(id_client) as id_client FROM CLIENT WHERE id_client="
				+ idClient;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		while (rs.next()) {
			if (rs.getInt("id_client") >= 1)
				throw new ExceptionClientExistant(msgException);
		}

		// Sinon on l'ajoute au cache et à la BDD
		cacheClients.put(idClient, c);
		FactorySQL
				.getInstance()
				.executeUpdate(
						"INSERT INTO CLIENT (id_client, prenom, nom, numero, nbPoint, nbHeureGratuite) "
								+ "VALUES ("
								+ idClient
								+ ",'"
								+ c.getPrenom()
								+ "','"
								+ c.getNom()
								+ "',"
								+ c.getNumero()
								+ ",0,0)");

		return c;
	}

	
	
	
	/**
	 * Recherche un client par numéro
	 * @param numero
	 * @return
	 * @throws SQLException
	 * @throws ExceptionClientInexistant
	 */
	public Client rechercherClient(int numero) throws SQLException, ExceptionClientInexistant {

		// On recherche d'abord dans le cache
		Iterator<Integer> it = cacheClients.keySet().iterator();
		Client c;

		while (it.hasNext()) {
			c = cacheClients.get(it);

			if (c.getNumero() == numero)
				return c;
		}

		
		// A ce stade le client n'est pas en cache, on recherche dans la BDD
		String sql = "SELECT id_client, prenom, nom, numero, nbPoint, nbHeureGratuite FROM CLIENT WHERE numero="
				+ numero;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last(); // On place le curseur sur la dernière ligne
		int nbLigne = rs.getRow(); // On récupère le numéro de ligne (si 0 alors client inexistant)
		rs.beforeFirst(); // On replace le curseur avant la première ligne
		
		if(nbLigne<=0)
			throw new ExceptionClientInexistant("Le client n'existe pas");
		
		
		
		// A ce stade le client existe, on récupère les informations et on return le Client
		String prenom = rs.getString("prenom");
		String nom = rs.getString("nom");
		int nbPoint = rs.getInt("nbPoint");
		int nbHeureGratuite = rs.getInt("nbHeureGratuite");
		
		c = new Client(prenom, nom, numero);
		c.setCarteFidelite(new CarteFidelite(c,nbPoint,nbHeureGratuite));
		
		// On ajoute le client dans le cache
		cacheClients.put(c.hashCode(), c);
		
		return c;
	}

	
	
	
	
	/**
	 * Recherche un client par prénom et nom
	 * @param prenom
	 * @param nom
	 * @return
	 * @throws SQLException
	 * @throws ExceptionClientInexistant
	 */
	public Client rechercherClient(String prenom, String nom) throws SQLException, ExceptionClientInexistant {

		// On recherche d'abord dans le cache
		Iterator<Integer> it = cacheClients.keySet().iterator();
		Client c;
		
		while (it.hasNext()) {
			c = cacheClients.get(it);
			
			if (c.getPrenom().equals(prenom) && c.getNom().equals(nom))
				return c;
		}

		
		// A ce stade le client n'est pas en cache, on recherche dans la BDD
		String sql = "SELECT id_client, prenom, nom, numero, nbPoint, nbHeureGratuite FROM CLIENT WHERE prenom='"
				+ prenom
				+ "' AND NOM='"
				+ nom
				+ "'";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last(); // On place le curseur sur la dernière ligne
		int nbLigne = rs.getRow(); // On récupère le numéro de ligne (si 0 alors client inexistant)
		rs.beforeFirst(); // On replace le curseur avant la première ligne
		
		
		// Si le client existe pas, on déclenche une Exception
		if(nbLigne<=0)
			throw new ExceptionClientInexistant("Le client n'existe pas");
		
		
		
		// A ce stade le client existe, on récupère les informations et on return le Client
		int numero = rs.getInt("numero");
		int nbPoint = rs.getInt("nbPoint");
		int nbHeureGratuite = rs.getInt("nbHeureGratuite");
		
		c = new Client(prenom, nom, numero);
		c.setCarteFidelite(new CarteFidelite(c,nbPoint,nbHeureGratuite));
		
		// On ajoute le client dans le cache
		cacheClients.put(c.hashCode(), c);
		
		return c;	
	}
	
	
	public ArrayList<Client> listeClient () throws SQLException {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		
		String sql = "SELECT id_client, prenom, nom, numero, nbPoint, nbHeureGratuite FROM CLIENT";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		
		Client c;
		String prenom;
		String nom;
		int numero;
		int nbPoint;
		int nbHeureGratuite;
		
		while(rs.next()) {
			prenom = rs.getString("prenom");
			nom = rs.getString("nom");
			numero = rs.getInt("numero");
			nbPoint = rs.getInt("nbPoint");
			nbHeureGratuite = rs.getInt("nbHeureGratuite");
			
			c = new Client(prenom,nom,numero);
			c.setCarteFidelite(new CarteFidelite(c, nbPoint, nbHeureGratuite));
			
			// Si le client existe en cache on retourne ce dernier pour pas surcharger le RAM, sinon on retourne celui créée ci-dessus
			if(cacheClients.containsKey(c.hashCode()))
				lesClients.add(cacheClients.get(c.hashCode()));
			else {
				lesClients.add(c);
			}
		}
		
		return lesClients;
	}
	

}
