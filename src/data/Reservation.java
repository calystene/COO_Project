package data;

import java.util.*;

import data.horaire.PlageHoraire;
import data.salle.Salle;

/**
 * Class Reservation
 */
public class Reservation {
	private Date datePriseReservation;
	private Date dateReservation;
	private ArrayList<PlageHoraire> lesPlages;
	private boolean etatPaiement;
	private int prix;
	private Client client;
	private Salle salle;
	private int duree;

	//
	// Constructors
	//
	public Reservation(Date dPR, Date dR, ArrayList<PlageHoraire> plages, int p,
			Client c, Salle s) {
		datePriseReservation = dPR;
		dateReservation = dR;
		lesPlages = plages;
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

	public ArrayList<PlageHoraire> getLesPlages() {
		return lesPlages;
	}

	public void setLesPlages(ArrayList<PlageHoraire> lesPlages) {
		this.lesPlages = lesPlages;
	}

	public int getPrix() {
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

	
}
