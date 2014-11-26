package data.forfait;

import java.util.Date;

import data.Client;

/**
 * Class Forfait12HGrande
 */
public class Forfait12HGrande extends Forfait {

	//
	// Fields
	//
	final private int PRIX = 80;
	final private String libelle = "Forfait 12h grande salle";
	
	private int heureDisponible;
	private Date dateFinValidite;

	//
	// Constructors
	//
	public Forfait12HGrande(int n, Client c) {
		super(n,c);
		heureDisponible = 12;
		
	}

	//
	// Methods
	//
	/**
	   * Set the value of dateFinValidite
	   * @param newVar the new value of dateFinValidite
	   */
	  public void setDateFinValidite (Date newVar) {
	    dateFinValidite = newVar;
	  }

	  /**
	   * Get the value of dateFinValidite
	   * @return the value of dateFinValidite
	   */
	  public Date getDateFinValidite () {
	    return dateFinValidite;
	  }

	  /**
	   * Set the value of heureDisponible
	   * @param newVar the new value of heureDisponible
	   */
	  public void setHeureDisponible (int newVar) {
	    heureDisponible = newVar;
	  }

	  /**
	   * Get the value of heureDisponible
	   * @return the value of heureDisponible
	   */
	  public int getHeureDisponible () {
	    return heureDisponible;
	  }

	  /**
	   * Set the value of prix
	   * @param newVar the new value of prix
	   */
	  public void setPrix (int newVar) {
	    prix = newVar;
	  }

	  /**
	   * Get the value of prix
	   * @return the value of prix
	   */
	  public int getPrix () {
	    return prix;
	  }

	  /**
	   * Set the value of libelle
	   * @param newVar the new value of libelle
	   */
	  public void setLibelle (String newVar) {
	    libelle = newVar;
	  }

	  /**
	   * Get the value of libelle
	   * @return the value of libelle
	   */
	  public String getLibelle () {
	    return libelle;
	  }

}
