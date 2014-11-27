package data.forfait;

import java.sql.Date;

import data.Client;

/**
 * Class Forfait12HPetite
 */
public class Forfait12HPetite extends Forfait {

	// Fields
	//
	final private int PRIX = 50;
	final private String LIBELLE = "Forfait 12h petite salle";
	
	private int heureDisponible;
	private Date dateFinValidite;

	//
	// Constructors
	//
	public Forfait12HPetite(int n, Client c) {
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
	   * Get the value of prix
	   * @return the value of prix
	   */
	  public int getPrix () {
	    return PRIX;
	  }


	  /**
	   * Get the value of libelle
	   * @return the value of libelle
	   */
	  public String getLibelle () {
	    return LIBELLE;
	  };

}
