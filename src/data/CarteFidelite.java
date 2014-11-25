package data;


/**
 * Class CarteFidelite
 */
public class CarteFidelite {

	//
	// Fields
	//

	private int nbPoints;
	private int nbHeureGratuite;
	private Client client;

	//
	// Constructors
	//
	public CarteFidelite(Client c) {
		client = c;
		nbPoints = 0;
		nbHeureGratuite = 0;
	};

	//
	// Methods
	//

	//
	// Accessor methods
	//

	/**
	 * Set the value of nbPoints
	 * 
	 * @param newVar
	 *            the new value of nbPoints
	 */
	public void setNbPoints(int newVar) {
		nbPoints = newVar;
	}

	/**
	 * Get the value of nbPoints
	 * 
	 * @return the value of nbPoints
	 */
	public int getNbPoints() {
		return nbPoints;
	}

	/**
	 * Set the value of nbHeureGratuite
	 * 
	 * @param newVar
	 *            the new value of nbHeureGratuite
	 */
	public void setNbHeureGratuite(int newVar) {
		nbHeureGratuite = newVar;
	}

	/**
	 * Get the value of nbHeureGratuite
	 * 
	 * @return the value of nbHeureGratuite
	 */
	public int getNbHeureGratuite() {
		return nbHeureGratuite;
	}

	//
	// Other methods
	//

	/**
	 * 
	 */
	public void miseAJour() {
		
	}
	
	
	/**
	 * 
	 * Get the value of client
	 */
	public Client getPersonne() {
		return client;
	}
	
	/**
	 * Set the value of client;
	 * @param p
	 */
	public void setPersonne(Client p) {
		client = p;
	}
}
