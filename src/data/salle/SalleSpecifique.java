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
	final private TYPE_SALLE categorie = TYPE_SALLE.GRANDE_SALLE;
	
	private String nom;
	
	//
	// Constructors
	//
	public SalleSpecifique(String n) {
		nom = n;
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
	
	
	/**
	 * 
	 * @return the category of the Salle
	 */
	public TYPE_SALLE getCategorie() {
		return categorie;
	}
}
