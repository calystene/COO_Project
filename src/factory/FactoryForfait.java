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
import exception.ExceptionForfaitExistant;

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

	// Modifier la classe : Param (Client c, TYPE_FORFAIT t) et rechercher les
	// informations en BDD
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
		// Date fin validité

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

	
	public Forfait rechercherForfait(int n) {

		return null;
	}

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
		}
		
		return listeForfait;
	}

	public ArrayList<Forfait> listeForfait() {
		
		return null;
	}

	public ArrayList<Forfait> listeTypeForfait() {
		
		return null;
	}
	
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
