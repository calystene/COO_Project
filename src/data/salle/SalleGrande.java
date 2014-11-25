package data.salle;

/**
 * Class SalleGrande
 */
public class SalleGrande implements Salle {

	//
	// Fields
	//
	final private int PRIX_1H = 10;
	final private int PRIX_2H = 16;
	
	private String nom;

	//
	// Constructors
	//
	public SalleGrande() {
	};

	//
	// Methods
	//

	//
	// Accessor methods
	//

	
	
	/**
	 * get the value of PRIX_1H
	 * @return PRIX_1H
	 */
	public int getPrix1h() {
		return PRIX_1H;
	}
	
	
	/**
	 * get the value of PRIX_2H
	 * @return PRIX_2H
	 */
	public int getPrix2h() {
		return PRIX_2H;
	}
	
	
	/**
	 * Set the value of nom
	 * 
	 * @param newVar
	 *            the new value of nom
	 */
	public void setNom(String newVar) {
		nom = newVar;
	}

	/**
	 * Get the value of nom
	 * 
	 * @return the value of nom
	 */
	public String getNom() {
		return nom;
	}

	//
	// Other methods
	//

	/**
	 * @return int
	 */
	public int getPrixUneHeure() {
		return 0;
	}

	/**
	 * @return int
	 */
	public int getPrixDeuxHeure() {
		return 0;
	}

}
