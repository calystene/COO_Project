package factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import data.Client;
import data.Reservation;
import data.horaire.PlageHoraire;
import data.salle.Salle;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;
import exception.ExceptionReservationExistante;
import exception.ExceptionReservationInexistante;
import exception.ExceptionSalleInexistante;

public class FactoryReservation {
	
	private static FactoryReservation singleton;
	
	private HashMap<Integer, Reservation> cacheReservation;
	
	private FactoryReservation() {
		cacheReservation = new HashMap<Integer, Reservation>();
	}

	public static FactoryReservation getInstance() {
		if (singleton == null)
			singleton = new FactoryReservation();

		return singleton;
	}
	
	/**
	 * Permet de creer une reservation dans le cache et dan s la BDD
	 * @param dPR
	 * @param dR
	 * @param plages
	 * @param p
	 * @param c
	 * @param s
	 * @param duree
	 * @return la reservation créée
	 * @throws SQLException, ExceptionReservationExistante
	 */
	public Reservation creerReservation (Date dPR, Date dR, PlageHoraire plage, int p, Client c, Salle s, int duree) throws ExceptionReservationExistante, SQLException{
		Reservation r = new Reservation (dPR, dR, plage, p, c, s, duree);
		int idReservation = r.hashCode();
		// On vérifie l'existence de la Reservation dans le cache
				if (cacheReservation.containsKey(idReservation))
					throw new ExceptionReservationExistante("La reservation existe déjà");

		// On vérifie si la Reservation existe en BDD
		String sql = "SELECT COUNT(id_reservation) as id_Reservation FROM RESERVATION WHERE id_reservation="+ idReservation;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			if (rs.getInt("id_reservation") >= 1)
				throw new ExceptionReservationExistante("La Reservation existe déjà");
		}
		
		// On l'ajoute au cache et à la BDD
		cacheReservation.put(idReservation, r);

		sql = "INSERT INTO RESERVATION (id_reservation, datePriseReservation, dateReservation, fk_plageHoraire, prix, fk_client, fk_salle, duree, etatPaiement)"
				+ " VALUES ("
				+ idReservation
				+ ", '"
				+ dPR
				+ "', '"
				+ dR
				+ "', "
				+ plage.hashCode()
				+ ", "
				+ p
				+ ", "
				+ c.hashCode()
				+ ",'"
				+ s.getNom().hashCode()
				+ "', "
				+ duree
				+",'"
				+r.getEtatPaiement()
				+ "')";
		
		FactorySQL.getInstance().executeUpdate(sql);
		
		//On retourne la reservation
		return r;
	}

	/**
	 * Permet de suppr une reservation de la BDD et du cache
	 * @param idReservation
	 */
	public void supprReservation(int idReservation){
		//Suppression dans le cache
		cacheReservation.remove(idReservation);
		//Suppression dans la BDD
		String sql = "DELETE FROM RESERVATION WHERE id_reservation='" + idReservation +"'" ;
		FactorySQL.getInstance().executeUpdate(sql);
	}
	
//	/**
//	 * Permet de retourner la liste des reservations du cache
//	 * @return listeReservationsCache
//	 */
//	public Iterator<Reservation> listeReservationsCache(){
//		return cacheReservation.values().iterator();	
//	}
	
//	/**
//	 * Permet de retourner la liste des reservations de la BDD
//	 * @return listeReservationBDD
//	 * @throws SQLException
//	 */
//	public ArrayList<Reservation> listeReservationBDD() throws SQLException {
//		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
//		String sql = "SELECT datePriseReservation, dateReservation, fk_plageHoraire, prix, fk_client, fk_salle, duree, etatPaiement FROM SALLE";
//		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
//
//		while (rs.next()) {
//			Date dPR = rs.getDate("datePriseReservation");
//			Date dR = rs.getDate("dateReservation");
//			PlageHoraire plage = null;
//			int p = rs.getInt("prix");
//			Client c = FactoryClient.getInstance().rechercherClient(rs.getInt("client"));
//			Salle s = FactorySalle.getInstance().rechercheSalle(rs.getString("salle"));
//			int duree = rs.getInt("duree");
//
//			Reservation r = new Reservation (dPR, dR,plages, p, c, s, duree);
//			lesReservations.add(r);
//		}
//		return lesReservations;
//	}
//	
	/**
	 * Permet de rechercher reservation grace a son id
	 * @param idReservation
	 * @return reservation 
	 * @throws SQLException
	 * @throws ExceptionReservationInexistante 
	 * @throws ExceptionPlageInexistante 
	 * @throws ExceptionClientInexistant 
	 * @throws ExceptionSalleInexistante 
	 */
	public Reservation rechercheReservation(int idReservation) throws SQLException, ExceptionReservationInexistante, ExceptionPlageInexistante, ExceptionClientInexistant, ExceptionSalleInexistante{		
		//On recherche dans le cache 
		Reservation r = cacheReservation.get(idReservation);
		if (r!=null){
			return r;		
		} else {
		//Sinon on recherche dans la BDD
		String sql = "SELECT datePriseReservation, dateReservation, fk_plageHoraire, prix, client, salle, nom, numero ,duree FROM RESERVATION ,CLIENT WHERE id_reservation="+ idReservation + "AND client = id_client";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last();
		int nbLigne = rs.getRow();
		if (nbLigne<=0) throw new ExceptionReservationInexistante("La Reservation n'existe pas");

		Date dPR = rs.getDate("datePriseReservation");
		Date dR = rs.getDate("dateReservation");
		PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
		int p = rs.getInt("prix");
		Client c = FactoryClient.getInstance().rechercherClient(rs.getString("nom"),rs.getInt("numero"));
		Salle s = FactorySalle.getInstance().rechercheSalle(rs.getInt("salle"));
		int duree = rs.getInt("duree");
	
		return new Reservation (dPR, dR,plage, p, c, s, duree);
		}
	}
	
	/**
	 * Permet de rechercher reservation grace a un client et une date de reservation
	 * @param dR
	 * @param c
	 * @return Reservation
	 * @throws SQLException
	 * @throws ExceptionPlageInexistante 
	 * @throws ExceptionSalleInexistante 
	 * @throws ExceptionReservationInexistante 
	 */
	public Reservation rechercheReservation(Date dR, Client c) throws SQLException, ExceptionPlageInexistante, ExceptionSalleInexistante, ExceptionReservationInexistante{
		//On recherche dans la BDD
		String sql = "SELECT datePriseReservation, dateReservation, fk_plageHoraire, prix, client, salle, nom, numero ,duree FROM RESERVATION ,CLIENT WHERE dateReservation="+ dR + "AND client =" + c.hashCode();
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last();
		int nbLigne = rs.getRow();
		if (nbLigne<=0) throw new ExceptionReservationInexistante("La Reservation n'existe pas");

		Date dPR = rs.getDate("datePriseReservation");
		PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
		int p = rs.getInt("prix");
		Salle s = FactorySalle.getInstance().rechercheSalle(rs.getInt("salle"));
		int duree = rs.getInt("duree");
	
		return new Reservation (dPR, dR,plage, p, c, s, duree);

	}
	
	/**
	 * Retourne la liste des reservation pour une date precisée
	 * @param dR
	 * @return listeReservationDate
	 * @throws SQLException
	 * @throws ExceptionPlageInexistante 
	 * @throws ExceptionClientInexistant 
	 * @throws ExceptionSalleInexistante 
	 */
	public ArrayList<Reservation> listeReservationDate(Date dR) throws SQLException, ExceptionPlageInexistante, ExceptionClientInexistant, ExceptionSalleInexistante{
		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
		String sql = "SELECT datePriseReservation, dateReservation, fk_plageHoraire, prix, client, salle, nom, numero ,duree FROM RESERVATION ,CLIENT WHERE dateReservation =" +dR  + "AND client = id_client";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			Date dPR = rs.getDate("datePriseReservation");
			PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
			int p = rs.getInt("prix");
			Client c = FactoryClient.getInstance().rechercherClient(rs.getString("nom"),rs.getInt("numero"));
			Salle s = FactorySalle.getInstance().rechercheSalle(rs.getInt("salle"));
			int duree = rs.getInt("duree");

			Reservation r = new Reservation (dPR, dR,plage, p, c, s, duree);
			lesReservations.add(r);
		}
		return lesReservations;
	}
	
	/**
	 *  Retourne la liste des reservation pour un client precisé
	 * @param c
	 * @return listeReservationClient
	 * @throws SQLException
	 * @throws ExceptionPlageInexistante 
	 * @throws ExceptionSalleInexistante 
	 */
	public ArrayList<Reservation> listeReservationClient(Client cl) throws SQLException, ExceptionPlageInexistante, ExceptionSalleInexistante{
		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
		String sql = "SELECT datePriseReservation, dateReservation, fk_plageHoraire, prix, client, salle, nom, numero ,duree FROM RESERVATION ,CLIENT WHERE client =" +cl.hashCode();
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			Date dR = rs.getDate("dateReservation");
			Date dPR = rs.getDate("datePriseReservation");
			PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
			int p = rs.getInt("prix");
			Salle s = FactorySalle.getInstance().rechercheSalle(rs.getInt("salle"));
			int duree = rs.getInt("duree");

			Reservation r = new Reservation (dPR, dR,plage, p, cl, s, duree);
			lesReservations.add(r);
		}
		return lesReservations;
	}
	
	/**
	 *  Retourne la liste des reservation pour une salle precisée
	 * @param s
	 * @return listeReservationSalle
	 * @throws SQLException
	 * @throws ExceptionPlageInexistante 
	 * @throws ExceptionClientInexistant 
	 */
	public ArrayList<Reservation> listeReservationSalle(Salle s) throws SQLException, ExceptionPlageInexistante, ExceptionClientInexistant{
		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
		String sql = "SELECT datePriseReservation, dateReservation, fk_plageHoraire, prix, client, salle, nom, numero ,duree FROM RESERVATION ,CLIENT WHERE salle =" + s.getNom().hashCode() + "AND client = id_client";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			Date dR = rs.getDate("dateReservation");
			Date dPR = rs.getDate("datePriseReservation");
			PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
			int p = rs.getInt("prix");
			Client c = FactoryClient.getInstance().rechercherClient(rs.getString("nom"),rs.getInt("numero"));
			int duree = rs.getInt("duree");

			Reservation r = new Reservation (dPR, dR,plage, p, c, s, duree);
			lesReservations.add(r);
		}
		return lesReservations;
	}
	
	
	
}