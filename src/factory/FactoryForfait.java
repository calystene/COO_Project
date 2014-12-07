package factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import util.date.DateManager;
import data.Client;
import data.forfait.Forfait;
import data.forfait.TYPE_FORFAIT;
import exception.ExceptionClientInexistant;
import exception.ExceptionForfaitExistant;
import exception.ExceptionForfaitInexistant;

public class FactoryForfait {
	private HashMap<Integer, Forfait> cacheForfait;
	private static FactoryForfait singleton;

	private FactoryForfait() {
		cacheForfait = new HashMap<Integer, Forfait>();
	}

	public static FactoryForfait getInstance() {
		if (singleton == null)
			singleton = new FactoryForfait();

		return singleton;
	}

	
	/**
	 * Permet de créer un nouveau forfait pour un client. L'ajoute au cache et à la BDD
	 * @param c Le client
	 * @param t Le type de forfait
	 * @return Le nouveau forfait pour le client
	 * @throws ExceptionForfaitExistant
	 * @throws SQLException
	 */
	public Forfait creerForfait(Client c, TYPE_FORFAIT t) throws ExceptionForfaitExistant,
			SQLException {

		int idForfait = Math.abs(t.hashCode() + c.hashCode());
		String msgException = "Le forfait existe déjà";

		// On vérifie l'existence du forfait dans le cache
		if (cacheForfait.containsKey(idForfait))
			throw new ExceptionForfaitExistant(msgException);

		// On vérifie si le forfait existe en BDD
		String sql = "SELECT COUNT(id_forfait) as id_forfait FROM FORFAIT WHERE id_forfait="
				+ idForfait;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			if (rs.getInt("id_forfait") >= 1)
				throw new ExceptionForfaitExistant(msgException);
		}

		// Sinon on récupère les informations sur le type de forfait dans la BDD
		// et on crée le forfait
		sql = "SELECT id_typeForfait, prix, libelle, nb_heure, nb_moisValide "
				+ "FROM TYPE_FORFAIT WHERE id_typeForfait='" + t.toString() + "'";
		rs = FactorySQL.getInstance().getResultSet(sql);

		int prix=0;
		int nb_heureInit=0;
		int nb_moisValide = 0;
		String libelle = "";

		while (rs.next()) {
			prix = rs.getInt("prix");
			nb_heureInit = rs.getInt("nb_heure");
			nb_moisValide = rs.getInt("nb_moisValide");
			libelle = rs.getString("libelle");
		}

		Date dateFinValidite = DateManager.addMonthFromToday(nb_moisValide);
		Forfait f = new Forfait(c, t, dateFinValidite, nb_heureInit, prix, libelle);
		
			
		// On l'ajoute au cache et à la BDD
		cacheForfait.put(f.getNumero(), f);
		

		sql = "INSERT INTO FORFAIT (id_forfait, date_FinValidite, nb_heureDisponible, fk_client, fk_typeForfait)"
				+ " VALUES ("
				+ f.getNumero()
				+ ", '"
				+ DateManager.dateToSQL(f.getDateFinValidite())
				+ "', "
				+ nb_heureInit
				+ ", "
				+ c.hashCode()
				+ ", '"
				+ t.toString() + "')";
		
		FactorySQL.getInstance().executeUpdate(sql);

		return f;
	}

	
	
	
	
	
	/**
	 * Permet de rechercher un forfait avec son numéro
	 * @param n
	 * @return
	 * @throws SQLException
	 * @throws ExceptionForfaitInexistant
	 * @throws ExceptionClientInexistant
	 */
	public Forfait rechercherForfait(int n) throws SQLException, ExceptionForfaitInexistant, ExceptionClientInexistant {
		// On recherche dans le cache en premier
		if(cacheForfait.containsKey(n))
			return cacheForfait.get(n);
		
		//Sinon on regarde dans la base
		String sql = "SELECT id_forfait, date_FinValidite, nb_heureDisponible, prix, libelle, fk_typeForfait, nom, numero FROM FORFAIT, CLIENT, TYPE_FORFAIT WHERE "
				+ "id_typeForfait=fk_typeForfait AND "
				+ "id_client=fk_client AND "
				+ "id_forfait=" + n;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		
		rs.last(); // On place le curseur sur la dernière ligne
		int nbLigne = rs.getRow(); // On récupère le numéro de ligne
		// Si numero de ligne <= 0 alors forfait inexistant
		if(nbLigne <= 0)
			throw new ExceptionForfaitInexistant("Le forfait " + n + " n'existe pas");
		
		
		// Sinon
		// Création de l'objet Forfait
		Forfait f = null; 
		
		// Données forfait
		TYPE_FORFAIT type = getTypeForfait(rs.getString("fk_typeForfait"));
		Date dFinValidite = DateManager.sqlToDate(rs.getDate("date_FinValidite"));
		int hDispo = rs.getInt("nb_heureDisponible");
		int prix = rs.getInt("prix");;
		String libelle = rs.getString("libelle");
		
		f = new Forfait(type, dFinValidite, hDispo, prix, libelle); // On crée le forfait
		f.setNumero(n);
		
		cacheForfait.put(f.getNumero(), f);
		
		return f;
	}

	
	
	/**
	 * Retourne la liste des forfait d'un client
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Forfait> rechercherByClient(Client c) throws SQLException {
		ArrayList<Forfait> listeForfait = new ArrayList<Forfait>();
		
		String sql = "SELECT id_forfait, date_FinValidite, nb_heureDisponible, prix, libelle, fk_typeForfait FROM FORFAIT, TYPE_FORFAIT WHERE "
				+ "fk_typeForfait=id_typeForfait AND "
				+ "fk_client=" + c.hashCode();
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		
		Forfait f = null;
		TYPE_FORFAIT type;
		Date dFinValidite;
		int hDispo;
		int prix;
		String libelle;
		
		while (rs.next()) {
			type = getTypeForfait(rs.getString("fk_typeForfait"));
			dFinValidite = DateManager.sqlToDate(rs.getDate("date_FinValidite"));
			hDispo = rs.getInt("nb_heureDisponible");
			prix = rs.getInt("prix");
			libelle = rs.getString("libelle");
			f = new Forfait(c, type, dFinValidite, hDispo, prix, libelle);
			
			listeForfait.add(f);
			cacheForfait.put(f.getNumero(), f);
		}
		
		return listeForfait;
	}

	
	
	
	/**
	 * Retourne la liste des types de forfait disponible dans la BDD
	 * @return La liste des types de forfait
	 * @throws SQLException
	 */
	public ArrayList<Forfait> listeTypeForfait() throws SQLException {
		ArrayList<Forfait> listeForfait = new ArrayList<Forfait>();
		
		String sql = "SELECT id_typeForfait, prix, libelle, nb_heure FROM TYPE_FORFAIT";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		
		Forfait f = null;
		TYPE_FORFAIT type;
		int hDispo;
		int prix;
		String libelle;
		
		while (rs.next()) {
			type = getTypeForfait(rs.getString("id_typeForfait"));
			hDispo = rs.getInt("nb_heure");
			prix = rs.getInt("prix");
			libelle = rs.getString("libelle");
			f = new Forfait(type, hDispo, prix, libelle);
			
			listeForfait.add(f);
		}
		
		return listeForfait;
	}
	
	
	
	
	/**
	 * Permet de faire la conversion entre l'objet TYPE_FORFAIT et le varchar typeForfait contenu dans la BDD
	 * @param s Le varchar typeForfait issu de la BDD
	 * @return l'objet TYPE_FORFAIT correspondant 
	 */
	private TYPE_FORFAIT getTypeForfait(String s) {
		if(s.equals("A_PETITE")) {
			return TYPE_FORFAIT.A_PETITE;
		} else if (s.equals("A_GRANDE")) {
			return TYPE_FORFAIT.A_GRANDE;
		} else if (s.equals("B_PETITE")) {
			return TYPE_FORFAIT.B_PETITE;
		} else if (s.equals("B_GRANDE")) {
			return TYPE_FORFAIT.B_GRANDE;
		}
		return null;
	}
}
