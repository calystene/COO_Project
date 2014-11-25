package data.horaire;

/**
 * Class TrancheH_Soir
 */
public class TrancheH_Soir implements TrancheHoraire {

  //
  // Fields
  //

  public String nom;
  public int debut;
  public int fin;
  
  //
  // Constructors
  //
  public TrancheH_Soir () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of nom
   * @param newVar the new value of nom
   */
  public void setNom (String newVar) {
    nom = newVar;
  }

  /**
   * Get the value of nom
   * @return the value of nom
   */
  public String getNom () {
    return nom;
  }

  /**
   * Set the value of debut
   * @param newVar the new value of debut
   */
  public void setDebut (int newVar) {
    debut = newVar;
  }

  /**
   * Get the value of debut
   * @return the value of debut
   */
  public int getDebut () {
    return debut;
  }

  /**
   * Set the value of fin
   * @param newVar the new value of fin
   */
  public void setFin (int newVar) {
    fin = newVar;
  }

  /**
   * Get the value of fin
   * @return the value of fin
   */
  public int getFin () {
    return fin;
  }

  //
  // Other methods
  //



}
