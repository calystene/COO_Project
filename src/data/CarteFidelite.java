package data;


/**
 * Class CarteFidelite
 */
public class CarteFidelite {

	private int nbPoint;
	private int nbHeureGratuite;
	private Client client;

	//
	// Constructors
	//
	public CarteFidelite(Client c) {
		client = c;
		nbPoint = 0;
		nbHeureGratuite = 0;
	};


	public CarteFidelite(Client c, int nbP, int nbHG) {
		client = c;
		nbPoint = nbP;
		nbHeureGratuite = nbHG;
	}

	
	
	/**
	 * Set the value of nbPoints
	 * 
	 * @param newVar
	 *            the new value of nbPoints
	 */
	public void setNbPoint(int newVar) {
		nbPoint = newVar;
	}

	/**
	 * Get the value of nbPoints
	 * 
	 * @return the value of nbPoints
	 */
	public int getNbPoint() {
		return nbPoint;
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
	 * Get the value of client
	 */
	public Client getPersonne() {
		return client;
	}
	
}
