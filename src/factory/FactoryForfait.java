package factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import data.Client;
import data.forfait.Forfait;
import data.forfait.TYPE_FORFAIT;
import exception.ExceptionClientExistant;
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
	public Forfait creerForfait(Client c, TYPE_FORFAIT t, Date dFinValidite,
			int hDispo, int p, String l) throws ExceptionForfaitExistant,
			SQLException {

		
		int idForfait = t.hashCode() + c.getNumero();
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

		
		// Sinon on récupère les informations sur le type de forfait dans la BDD et on cré le forfait
		sql = "SELECT id_typeForfait, prix, libelle, nb_heure, nb_moisValide "
				+ "FROM TYPE_FORFAIT WHERE id_forfait='" + t.toString() + "'";
		rs = FactorySQL.getInstance().getResultSet(sql);
		
		int prix, nb_heure, nb_moisValide;
		String libelle;
		// Date fin validité
		
		while (rs.next()) {
			prix = rs.getInt("prix");
			nb_heure = rs.getInt("nb_heure");
			nb_moisValide = rs.getInt("nb_moisValide");
			libelle = rs.getString("libelle");
		}
		
		Forfait f = new Forfait(c, t, dFinValidite, hDispo, p, l);
		
		// Sinon on l'ajoute au cache et à la BDD
		
		
		
		cacheForfait.put(f.getNumero(), f);
		int nbHeureInitial = 0; // Récupérer les informations dans la BDD sur le
								// type de forfait

		sql = "INSERT INTO FORFAIT (id_forfait, dateFinValide, nb_heureDisponible, fk_client, fk_typeForfait)"
				+ " VALUES ("
				+ f.getNumero()
				+ ", "
				+ f.getDateFinValidite() // à transformer en long ou Date SQL
				+ ", "
				+ nbHeureInitial
				+ ", "
				+ c.getNumero()
				+ ", "
				+ t.toString() + ")";
		FactorySQL.getInstance().executeUpdate(sql);

		return f;
	}

	public Forfait rechercherForfait(int n) {

		return null;
	}

	public ArrayList<Forfait> rechercherByClient(Client c) {

		return null;
	}

	public ArrayList<Forfait> listeForfait() {

		return null;
	}

	public ArrayList<Forfait> listeTypeForfait() {

		return null;
	}
}
