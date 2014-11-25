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
  private void setNumero (int newVar) {
    numero = newVar;
  }

  /**
   * Get the value of numero
   * @return the value of numero
   */
  private int getNumero () {
    return numero;
  }

  /**
   * Set the value of dateFinValidite
   * @param newVar the new value of dateFinValidite
   */
  private void setDateFinValidite (Date newVar) {
    dateFinValidite = newVar;
  }

  /**
   * Get the value of dateFinValidite
   * @return the value of dateFinValidite
   */
  private Date getDateFinValidite () {
    return dateFinValidite;
  }

  /**
   * Set the value of heureDisponible
   * @param newVar the new value of heureDisponible
   */
  private void setHeureDisponible (int newVar) {
    heureDisponible = newVar;
  }

  /**
   * Get the value of heureDisponible
   * @return the value of heureDisponible
   */
  private int getHeureDisponible () {
    return heureDisponible;
  }

  /**
   * Set the value of prix
   * @param newVar the new value of prix
   */
  private void setPrix (int newVar) {
    prix = newVar;
  }

  /**
   * Get the value of prix
   * @return the value of prix
   */
  private int getPrix () {
    return prix;
  }

  /**
   * Set the value of libelle
   * @param newVar the new value of libelle
   */
  private void setLibelle (String newVar) {
    libelle = newVar;
  }

  /**
   * Get the value of libelle
   * @return the value of libelle
   */
  private String getLibelle () {
    return libelle;
  }


}
