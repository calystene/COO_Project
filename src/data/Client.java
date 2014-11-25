package data;

/**
 * Class Client
 */
public class Client {

  //
  // Fields
  //

  private String prenom;
  private String nom;
  private int numero;
  private CarteFidelite carte;
  
  //
  // Constructors
  //
  public Client (String p, String no, int nu) { 
	  prenom = p;
	  nom = no;
	  numero = nu;
  };
  
  //
  // Methods
  //

  //
  // Accessor methods
  //

  /**
   * Set the value of prenom
   * @param newVar the new value of prenom
   */
  public void setPrenom (String newVar) {
    prenom = newVar;
  }

  /**
   * Get the value of prenom
   * @return the value of prenom
   */
  public String getPrenom () {
    return prenom;
  }

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
   * Set the value of carte
   * @param c
   */
  public void setCarteFidelite(CarteFidelite c) {
	  carte = c;
  }
  
  
  /**
   * Get the value of carte
   * @return
   */
  public CarteFidelite getCarteFidelite() {
	  return carte;
  }
  //
  // Other methods
  //

}
