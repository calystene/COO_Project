package data.salle;

/**
 * Class SallePetite
 */
public class SallePetite implements Salle {

	//
	// Fields
	//
	
	final private int PRIX_1H = 7;
	final private int PRIX_2H = 10;
	
	private String nom;

	//
	// Constructors
	//
	public SallePetite() {
	};

	//
	// Methods
	//

	//
	// Accessor methods
	//

	
	
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
	 * Get the value of PRIX_1H
	 * @return int
	 */
	public int getPrixUneHeure() {
		return PRIX_1H;
	}

	/**
	 * Get the value of PRIX_2H
	 * @return int
	 */
	public int getPrixDeuxHeure() {
		return PRIX_2H;
	}

}
