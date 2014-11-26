package data.forfait;

import data.Client;


/**
 * Class Forfait
 */
abstract public class Forfait {

  //
  // Fields
  //

  private int numero;
  private Client client;
  
  
  //
  // Constructors
  //
  public Forfait (int n, Client c) {
	  numero = n;
	  client = c;
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
  
  
  public Client getClient () {
	  return client;
	  // return FactoryClient.rechercherByForfait(this);
  }
  
  

}
