package data.forfait;
import java.util.*;


/**
 * Class Forfait
 */
abstract public class Forfait {

  //
  // Fields
  //

  private int numero;
  private Date dateFinValidite;
  private int heureDisponible;
  private int prix;
  private String libelle;
  
  //
  // Constructors
  //
  public Forfait () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of numero
   * @param newVar the new value of numero
   */
  public void setNumero (int newVar) {
    numero = newVar;
  }

  /**
   * Get the value of numero
   * @return the value of numero
   */
  public int getNumero () {
    return numero;
  }

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
