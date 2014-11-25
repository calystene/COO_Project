package data.salle;

/**
 * Class SalleSpecifique
 */
public class SalleSpecifique implements Salle {

	//
	// Fields
	//
	final private int PRIX_1H = 20;
	final private int PRIX_2H = 30;

	private String nom;

	//
	// Constructors
	//
	public SalleSpecifique() {
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
	private void setNom(String newVar) {
		nom = newVar;
	}

	/**
	 * Get the value of nom
	 * 
	 * @return the value of nom
	 */
	private String getNom() {
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
