package data;

import java.util.*;

import util.date.DateManager;
import data.horaire.PlageHoraire;
import data.salle.Salle;

/**
 * Class Reservation
 */
public class Reservation {
	private Date datePriseReservation;
	private Date dateReservation;
	private PlageHoraire plage;
	private boolean etatPaiement;
	private float prix;
	private Client client;
	private Salle salle;
	private int duree;

	//
	// Constructors
	//
	public Reservation(Date dPR, Date dR, PlageHoraire pl, float p, Client c,
			Salle s, int duree) {

		datePriseReservation = dPR;
		dateReservation = dR;
		plage = pl;
		etatPaiement = false;
		prix = p;
		client = c;
		salle = s;
	};

	/**
	 * Set the value of dateReservation
	 * 
	 * @param newVar
	 *            the new value of dateReservation
	 */
	public void setDateReservation(Date newVar) {
		dateReservation = newVar;
	}

	/**
	 * Get the value of dateReservation
	 * 
	 * @return the value of dateReservation
	 */
	public Date getDateReservation() {
		return dateReservation;
	}

	/**
	 * Set the value of duree
	 * 
	 * @param newVar
	 *            the new value of duree
	 */
	public void setDuree(int newVar) {
		duree = newVar;
	}

	/**
	 * Get the value of duree
	 * 
	 * @return the value of duree
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * Set the value of etatPaiement
	 * 
	 * @param newVar
	 *            the new value of etatPaiement
	 */
	public void setEtatPaiement(boolean newVar) {
		etatPaiement = newVar;
	}

	/**
	 * Get the value of etatPaiement
	 * 
	 * @return the value of etatPaiement
	 */
	public boolean getEtatPaiement() {
		return etatPaiement;
	}

	public PlageHoraire getPlage() {
		return plage;
	}

	public void setPlages(PlageHoraire plage) {
		this.plage = plage;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Date getDatePriseReservation() {
		return datePriseReservation;
	}

	
	public String toString () {
		return "Réservation le " + DateManager.valueOf(getDateReservation()) + " de la salle " + getSalle().getNom();
	}
	
	/**
	 * retourne le hashCode du client, sert d'ID dans la BDD et dans le HashMap
	 * de FactoryClient
	 * 
	 * @return le hashCode du client
	 */
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + plage.getTranche().hashCode();
		hash = hash * 21 + client.hashCode();
		hash = hash * 11 + dateReservation.hashCode();
		
		return Math.abs(hash);
	}

}
