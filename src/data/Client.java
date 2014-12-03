package data;

import java.sql.SQLException;
import java.util.ArrayList;

import data.forfait.Forfait;
import factory.FactoryForfait;

/**
 * Class Client
 */
public class Client {

	private String prenom;
	private String nom;
	private int numero;
	private CarteFidelite carte;
	private ArrayList<Forfait> lesForfaits;
	

	// Constructors
	//
	public Client(String p, String no, int nu) {
		prenom = p;
		nom = no;
		numero = nu;
		lesForfaits = new ArrayList<Forfait>();
	};


	/**
	 * Set the value of prenom
	 * 
	 * @param newVar
	 *            the new value of prenom
	 */
	public void setPrenom(String newVar) {
		prenom = newVar;
	}

	/**
	 * Get the value of prenom
	 * 
	 * @return the value of prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Set the value of nom
	 * 
	 * @param newVar
	 *            the new value of nom
	 */
	public void setNom(String newVar) {
		nom = newVar;
	}

	/**
	 * Get the value of nom
	 * 
	 * @return the value of nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Set the value of numero
	 * 
	 * @param newVar
	 *            the new value of numero
	 */
	public void setNumero(int newVar) {
		numero = newVar;
	}

	/**
	 * Get the value of numero
	 * 
	 * @return the value of numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Set the value of carte
	 * 
	 * @param c
	 */
	public void setCarteFidelite(CarteFidelite c) {
		carte = c;
	}

	/**
	 * Get the value of carte
	 * 
	 * @return the value of carte
	 */
	public CarteFidelite getCarteFidelite() {
		return carte;
	}

	//
	// Other methods
	//
	
	/**
	 * Add one Forfait to this Client
	 * @param f
	 */
	public void addForfait(Forfait f) {
		lesForfaits.add(f);
	}
	
	
	/**
	 * get the List of Forfait for this Client
	 * @return ArrayList<Forfait>
	 * @throws SQLException 
	 */
	public ArrayList<Forfait> getForfait() throws SQLException {
		//return lesForfaits;
		
		// Evolution : Utiliser méthode de Factory pour chercher l'information dans la base 
		return FactoryForfait.getInstance().rechercherByClient(this);
	}
	
	
	/**
	 * 
	 * 
	 */
	public ArrayList<Reservation> getReservation () {
		// FactoryReservation.getInstance().rechercherByClient(this);
		return null;
	}
	
	
	/**
	 * check equality between 2 Client objects
	 * @return true if objects are the same 
	 */
	public boolean equals(Object o) {
		if (o instanceof Client) {
			Client c = (Client) o;
			
			return (this.hashCode()==c.hashCode()); 
		}
		
		return false;
	}
	
	
	/**
	 * retourne le hashCode du client, sert d'ID dans la BDD et dans le HashMap de FactoryClient
	 * @return le hashCode du client
	 */
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + numero;
		hash = hash * 21 + nom.hashCode();
		hash = hash * 11 + prenom.hashCode();
		
		return Math.abs(hash);
	}
	
	/**
	 * retourne un string composé du prenom et du nom du Client
	 * 
	 */
	public String toString() {
		return prenom + " " + nom;
	}
}
