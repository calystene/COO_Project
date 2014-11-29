package data;

import data.horaire.PlageHoraire;
import data.salle.Salle;

/**
 * Class Tarif
 */
public class Tarif {

	private int prix;
	private PlageHoraire plage;
	private Salle salle;

	//
	// Constructors
	//
	public Tarif(PlageHoraire p, Salle s) {
		plage = p;
		salle = s;
	};

	/**
	 * Set the value of prix
	 * 
	 * @param newVar
	 *            the new value of prix
	 */
	public void setPrix(int newVar) {
		prix = newVar;
	}

	/**
	 * Get the value of prix
	 * 
	 * @return the value of prix
	 */
	public int getPrix() {
		return prix;
	}

	public PlageHoraire getPlage() {
		return plage;
	}

	public void setPlage(PlageHoraire plage) {
		this.plage = plage;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

}
