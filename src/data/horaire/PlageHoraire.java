package data.horaire;

import exception.ExceptionPlageIncorrect;

/**
 * Interface TrancheHoraire
 */
public class PlageHoraire {
	private int heureDebut;
	private int heureFin;
	private int duree;
	private Tranche tranche;
	
	
	public PlageHoraire(int hD, int hF, Tranche t) throws ExceptionPlageIncorrect {
		heureDebut = hD;
		heureFin = hF;
		duree = hD - hF;
		tranche = t;
		if (duree>2){
			throw new ExceptionPlageIncorrect("Plage horaire supérieur à 2 heures");
		}
	}


	public int getHeureDebut() {
		return heureDebut;
	}


	public void setHeureDebut(int heureDebut) {
		this.heureDebut = heureDebut;
	}


	public int getHeureFin() {
		return heureFin;
	}


	public void setHeureFin(int heureFin) {
		this.heureFin = heureFin;
	}


	public Tranche getTranche() {
		return tranche;
	}
	
	public int getDuree() {
		return duree;
	}
}
