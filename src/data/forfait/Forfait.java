package data.forfait;

import java.sql.SQLException;
import java.util.Date;

import data.Client;
import exception.ExceptionClientInexistant;
import exception.ExceptionForfaitInexistant;
import factory.FactoryClient;

/**
 * Class Forfait
 */
public class Forfait {

	private int numero;
	private TYPE_FORFAIT type;
	private Date dateFinValidite;
	private int heureDisponible;
	private int prix;
	private String libelle;
	
	

	
	/**
	 * Ce constructeur est fait pour pouvoir créer un forfait sans connaître le Client à l'avance
	 * Le forfait ne peut avoir d'existence propre dans le BDD sans qu'un client lui soit attribué
	 * @param t Le type de forfait
	 * @param dFinValidite La date de fin de validite du forfait
	 * @param hDispo Le nombre d'heure initial disponible
	 * @param p Le prix du forfait
	 * @param l Le libellé du forfait
	 */
	public Forfait(TYPE_FORFAIT t, Date dFinValidite, int hDispo, int p, String l) {
		type = t;
		dateFinValidite = dFinValidite;
		heureDisponible = hDispo;
		prix = p;
		libelle = l;
	}
	
	
	/**
	 * Constructeur pour crééer les différents Type de forfait disponible.
	 * Le forfait créer par ce constructeur n'a pas d'existence dans le BDD dans la table Forfait
	 * @param t Le type du forfait
	 * @param hDispo Le nombre d'heure initial du forfait
	 * @param p Le prix du forfait
	 * @param l Le libellé du forfait
	 */
	public Forfait(TYPE_FORFAIT t, int hDispo, int p, String l) {
		numero = 0;
		type = t;
		dateFinValidite = null;
		heureDisponible = hDispo;
		prix = p;
		libelle = l;
	}

	
	
	/**
	 * Function Forfait.java
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}


	/**
	 * Function Forfait.java
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}


	/**
	 * Function Forfait.java
	 * @return the client
	 * @throws ExceptionForfaitInexistant 
	 * @throws SQLException 
	 * @throws ExceptionClientInexistant 
	 */
	public Client getClient() throws SQLException, ExceptionClientInexistant {
		return FactoryClient.getInstance().rechercherByForfait(this);
	}



	/**
	 * Function Forfait.java
	 * @return the type
	 */
	public TYPE_FORFAIT getType() {
		return type;
	}


	/**
	 * Function Forfait.java
	 * @param type the type to set
	 */
	public void setType(TYPE_FORFAIT type) {
		this.type = type;
	}


	/**
	 * Function Forfait.java
	 * @return the dateFinValidite
	 */
	public Date getDateFinValidite() {
		return dateFinValidite;
	}


	/**
	 * Function Forfait.java
	 * @param dateFinValidite the dateFinValidite to set
	 */
	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}


	/**
	 * Function Forfait.java
	 * @return the heureDisponible
	 */
	public int getHeureDisponible() {
		return heureDisponible;
	}


	/**
	 * Function Forfait.java
	 * @param heureDisponible the heureDisponible to set
	 */
	public void setHeureDisponible(int heureDisponible) {
		this.heureDisponible = heureDisponible;
	}


	/**
	 * Function Forfait.java
	 * @return the prix
	 */
	public int getPrix() {
		return prix;
	}


	/**
	 * Function Forfait.java
	 * @param prix the prix to set
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}


	/**
	 * Function Forfait.java
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}


	/**
	 * Function Forfait.java
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
	


}
