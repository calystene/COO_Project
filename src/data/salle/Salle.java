package data.salle;


/**
 * Class Salle
 */
public class Salle {

	//Attributs
	private String nom;
	private TYPE_SALLE typeSalle;
	private int prix1H;
	private int prix2H;
	
	//Constructeur
    public Salle(String nom, int prix1H, int prix2H, TYPE_SALLE typeSalle) {
    	this.prix1H = prix1H;
    	this.prix2H = prix2H;
		this.nom = nom;
		this.typeSalle = typeSalle;
	}
	
	/**
     * Get the price for 1H
     * @return prix1H
     */
    public int getPrix1H() {
		return prix1H;
	}

    /**
     * Set the price for 1H
     * @param prix1h
     */
	public void setPrix1H(int prix1h) {
		prix1H = prix1h;
	}

	/**
     * Get the price for 2H
     * @return prix2H
     */
	public int getPrix2H() {
		return prix2H;
	}

    /**
     * Set the price for 2H
     * @param prix2h
     */
	public void setPrix2H(int prix2h) {
		prix2H = prix2h;
	}

	/**
     * Get the value of nom
     * @return nom
     */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Set the value of nom	
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}	
	
	/**
	 * 	Get the value of typeSalle
	 * @return typeSalle
	 */
	public TYPE_SALLE getTypeSalle() {
		return typeSalle;
	}	
	
	/**
	 * Set the value of typeSalle
	 * @param typeSalle
	 */
	public void setTypeSalle(TYPE_SALLE typeSalle) {
		this.typeSalle = typeSalle;
	}

	@Override
	public String toString() {
		return "Salle [nom=" + nom + ", typeSalle=" + typeSalle + ", prix1H="
				+ prix1H + ", prix2H=" + prix2H + "]";
	}
	
	
}

