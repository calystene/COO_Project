package data;
import java.util.*;


/**
 * Class Reservation
 */
public class Reservation {

  //
  // Fields
  //

  private Date dateReservation;
  private int duree;
  private boolean etatPaiement;
  
  //
  // Constructors
  //
  public Reservation () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of dateReservation
   * @param newVar the new value of dateReservation
   */
  private void setDateReservation (Date newVar) {
    dateReservation = newVar;
  }

  /**
   * Get the value of dateReservation
   * @return the value of dateReservation
   */
  private Date getDateReservation () {
    return dateReservation;
  }

  /**
   * Set the value of duree
   * @param newVar the new value of duree
   */
  private void setDuree (int newVar) {
    duree = newVar;
  }

  /**
   * Get the value of duree
   * @return the value of duree
   */
  private int getDuree () {
    return duree;
  }

  /**
   * Set the value of etatPaiement
   * @param newVar the new value of etatPaiement
   */
  private void setEtatPaiement (boolean newVar) {
    etatPaiement = newVar;
  }

  /**
   * Get the value of etatPaiement
   * @return the value of etatPaiement
   */
  private boolean getEtatPaiement () {
    return etatPaiement;
  }

  //
  // Other methods
  //

  /**
   * @return       boolean
   */
  public boolean estAnnulable()
  {
	  return false;
  }


}
