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
