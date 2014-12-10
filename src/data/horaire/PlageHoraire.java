package data.horaire;



/**
 * Interface TrancheHoraire
 */
public class PlageHoraire {
	private int heureDebut;
	private int heureFin;
	private int duree;
	private TRANCHE tranche;
	
	
	public PlageHoraire(int hD, int hF, TRANCHE t){
		heureDebut = hD;
		heureFin = hF;
		duree = hF - hD;
		tranche = t;
	}


	@Override
	public String toString() {
		return "PlageHoraire [heureDebut=" + heureDebut + ", heureFin="
				+ heureFin + ", duree=" + duree + ", tranche=" + tranche + "]";
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


	public TRANCHE getTranche() {
		return tranche;
	}
	
	public int getDuree() {
		return duree;
	}
	
	public int hashCode(){
		int hash = 1;
		hash = hash * 11 + heureDebut;
		hash = hash * 12 + heureFin;
		return hash;		
	}

	
}
