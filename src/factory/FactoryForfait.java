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

	public Forfait creerForfait(int n, TYPE_FORFAIT t, Date dFinValidite,
			int hDispo, int p, String l) throws ExceptionForfaitExistant,
			SQLException {
		Forfait f = new Forfait(n, t, dFinValidite, hDispo, p, l);
		String msgException = "Le forfait existe déjà";

		// On vérifie l'existence du forfait dans le cache
		if (cacheForfait.containsKey(f.getNumero()))
			throw new ExceptionForfaitExistant(msgException);

		// On vérifie si le forfait existe en BDD
		String sql = "SELECT COUNT(id_forfait) as id_forfait FROM FORFAIT WHERE id_forfait="
				+ f.getNumero();
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			if (rs.getInt("id_forfait") >= 1)
				throw new ExceptionForfaitExistant(msgException);
		}

		// Sinon on l'ajoute au cache et à la BDD
		cacheForfait.put(f.getNumero(), f);
		sql = "INSERT INTO FORFAIT (id_forfait, dateFinValide, ) VALUES ()";
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
