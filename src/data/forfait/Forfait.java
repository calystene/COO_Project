package data.forfait;

import java.util.Date;

import data.Client;

/**
 * Class Forfait
 */
public class Forfait {

	private int numero;
	private Client client;
	private TYPE_FORFAIT type;
	private Date dateFinValidite;
	private int heureDisponible;
	private int prix;
	private String libelle;
	
	
	//
	// Constructors
	//
	public Forfait(int n, TYPE_FORFAIT t, Date dFinValidite, int hDispo, int p, String l) {
		numero = n;
		type = t;
		dateFinValidite = dFinValidite;
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
	 */
	public Client getClient() {
		return client;
	}


	/**
	 * Function Forfait.java
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
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
