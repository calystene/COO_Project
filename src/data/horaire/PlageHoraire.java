package data.horaire;

/**
 * Interface TrancheHoraire
 */
public class PlageHoraire {

	private int heureDebut;
	private int heureFin;
	private Tranche tranche;
	
	
	public PlageHoraire(int hD, int hF, Tranche t) {
		heureDebut = hD;
		heureFin = hF;
		tranche = t;
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
	

}
