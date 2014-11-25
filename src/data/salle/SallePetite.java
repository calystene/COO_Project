package data.salle;

/**
 * Class SallePetite
 */
public class SallePetite implements Salle {

  //
  // Fields
  //

  private String nom;
  
  //
  // Constructors
  //
  public SallePetite () { };
  
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
  private void setNom (String newVar) {
    nom = newVar;
  }

  /**
   * Get the value of nom
   * @return the value of nom
   */
  private String getNom () {
    return nom;
  }

  //
  // Other methods
  //

  /**
   * @return       int
   */
  public int getPrixUneHeure()
  {
	  return 0;
  }


  /**
   * @return       int
   */
  public int getPrixDeuxHeure()
  {
	  return 0;
  }


}
