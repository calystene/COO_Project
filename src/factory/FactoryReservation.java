package factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import util.date.DateManager;
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
	public Reservation creerReservation (Date dPR, Date dR, PlageHoraire plage, float p, Client c, Salle s, int duree) throws ExceptionReservationExistante, SQLException{
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
				+ s.hashCode()
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
		String sql = "DELETE FROM RESERVATION WHERE id_reservation=" + idReservation;
		
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
		String sql = "SELECT datePriseReservation, dateReservation, prix, duree, nom, numero, etatpaiement, fk_plageHoraire, fk_salle FROM RESERVATION, CLIENT WHERE "
				+ "id_reservation=" +  idReservation 
				+ " AND fk_client = id_client";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last();
		int nbLigne = rs.getRow();
		if (nbLigne<=0) throw new ExceptionReservationInexistante("La Reservation n'existe pas");

		Date dPR = rs.getDate("datePriseReservation");
		Date dR = rs.getDate("dateReservation");
		
		PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
		
		float p = rs.getFloat("prix");
		Client c = FactoryClient.getInstance().rechercherClient(rs.getString("nom"),rs.getInt("numero"));
		Salle s = FactorySalle.getInstance().rechercheSalle(rs.getInt("fk_salle"));
		
		int duree = rs.getInt("duree");
		
		r = new Reservation (dPR, dR,plage, p, c, s, duree);
		r.setEtatPaiement(rs.getBoolean("etatpaiement"));
		
		return r;
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
		String sql = "SELECT datePriseReservation, dateReservation, prix, duree, etatpaiement, fk_plageHoraire, fk_salle FROM RESERVATION, CLIENT WHERE "
				+ "dateReservation='"+ DateManager.dateToSQL(dR) 
				+ "' AND fk_client =" + c.hashCode();
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last();
		int nbLigne = rs.getRow();
		if (nbLigne<=0) throw new ExceptionReservationInexistante("La Reservation n'existe pas");

		Date dPR = rs.getDate("datePriseReservation");
		PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
		float p = rs.getFloat("prix");
		Salle s = FactorySalle.getInstance().rechercheSalle(rs.getInt("fk_salle"));
		int duree = rs.getInt("duree");
		Reservation r = new Reservation (dPR, dR,plage, p, c, s, duree);
		r.setEtatPaiement(rs.getBoolean("etatpaiement"));
		
		return r;

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
	public ArrayList<Reservation> listeReservationDate(Date dR) throws SQLException, ExceptionPlageInexistante, ExceptionClientInexistant, ExceptionSalleInexistante {
		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
		String sql = "SELECT datePriseReservation, dateReservation, fk_plageHoraire, prix, fk_salle, nom, numero, duree, etatpaiement "
				+ "FROM RESERVATION, CLIENT "
				+ "WHERE dateReservation ='" +dR  + "' AND fk_client=id_client "
				+ "ORDER BY dateReservation";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			Date dPR = rs.getDate("datePriseReservation");
			PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
			
			float p = rs.getFloat("prix");
			Client c = FactoryClient.getInstance().rechercherClient(rs.getString("nom"),rs.getInt("numero"));
			Salle s = FactorySalle.getInstance().rechercheSalle(rs.getInt("fk_salle"));
			int duree = rs.getInt("duree");

			Reservation r = new Reservation (dPR, dR,plage, p, c, s, duree);
			r.setEtatPaiement(rs.getBoolean("etatpaiement"));
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
	public ArrayList<Reservation> rechercherByClient(Client cl) throws SQLException, ExceptionPlageInexistante, ExceptionSalleInexistante{
		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
		String sql = "SELECT datePriseReservation, dateReservation, prix, duree, etatpaiement, fk_plagehoraire, fk_salle FROM RESERVATION, CLIENT, SALLE WHERE "
				+ "fk_salle = id_salle AND "
				+ "fk_client = id_client AND "
				+ "fk_client =" +cl.hashCode();
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			Date dR = rs.getDate("dateReservation");
			Date dPR = rs.getDate("datePriseReservation");
			PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
			float p = rs.getFloat("prix");
			Salle s = FactorySalle.getInstance().rechercheSalle(rs.getInt("fk_salle"));
			int duree = rs.getInt("duree");

			Reservation r = new Reservation (dPR, dR,plage, p, cl, s, duree);
			r.setEtatPaiement(rs.getBoolean("etatpaiement"));
			
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
	public ArrayList<Reservation> rechercherBySalle(Salle s) throws SQLException, ExceptionPlageInexistante, ExceptionClientInexistant{
		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
		String sql = "SELECT datePriseReservation, dateReservation, prix, CLIENT.nom, numero, duree, heure_debut, heure_fin, tranche, etatpaiement, fk_plageHoraire FROM RESERVATION, CLIENT, PLAGE_HORAIRE WHERE "
				+ "fk_client = id_client AND "
				+ "fk_plagehoraire = id_plagehoraire AND "
				+ "fk_salle ='" + s.hashCode() + "' "
				+ "ORDER BY datereservation, heure_debut";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			Date dR = rs.getDate("dateReservation");
			Date dPR = rs.getDate("datePriseReservation");
			
			PlageHoraire plage = FactoryPlageHoraire.getInstance().rechercherPlageHoraire(rs.getInt("fk_plageHoraire"));
			float p = rs.getFloat("prix");
			Client c = FactoryClient.getInstance().rechercherClient(rs.getString("nom"),rs.getInt("numero"));
			int duree = rs.getInt("duree");

			Reservation r = new Reservation (dPR, dR,plage, p, c, s, duree);
			r.setEtatPaiement(rs.getBoolean("etatpaiement"));
			
			lesReservations.add(r);
		}
		
		return lesReservations;
	}
	
	/**
	 * Permet de mettre à jours une Réservation
	 * @param r
	 */
	public void majReservation(Reservation r) {
		System.out.println(r.hashCode());
		String sql = "UPDATE RESERVATION SET " 
				+ "etatpaiement='" + r.getEtatPaiement() + "',"
				+ "prix = " + r.getPrix() + " WHERE id_reservation=" + r.hashCode();
		
		System.out.println("maj" + FactorySQL.getInstance().executeUpdate(sql));
	}
	
}