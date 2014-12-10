package factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import data.horaire.PlageHoraire;
import data.horaire.TRANCHE;
import exception.ExceptionPlageInexistante;

public class FactoryPlageHoraire {
	private static FactoryPlageHoraire singleton;
	
	private FactoryPlageHoraire () {
	}
	
	public static FactoryPlageHoraire getInstance() {
		if (singleton==null) 
			singleton = new FactoryPlageHoraire();
		return singleton;
	}
	
	
	public PlageHoraire creerPlageHoraire (int heureD, int heureF, TRANCHE t) throws SQLException, ExceptionPlageInexistante{
		PlageHoraire ph = new PlageHoraire(heureD, heureF, t);
		int idPlageHoraire = ph.hashCode();

		// On vérifie si la plage existe en BDD
		String sql = "SELECT COUNT(id_plageHoraire) as id_plageHoraire FROM PLAGE_HORAIRE WHERE id_plageHoraire="+ idPlageHoraire;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			if (rs.getInt("id_plageHoraire") >= 1)
				return ph;
		}
		
		// Sinon on l'ajoute à la BDD
		sql = "INSERT INTO plage_Horaire (id_plageHoraire, heure_debut, heure_fin, tranche )"
				+ " VALUES ("
				+ idPlageHoraire
				+ ", "
				+ heureD
				+ ", "
				+ heureF
				+ ",'"
				+ t.toString()
				+ "')";
		
		FactorySQL.getInstance().executeUpdate(sql);
		
		//On retourne la reservation
		return ph;
	}
	
	public PlageHoraire rechercherPlageHoraire(int id_plage) throws SQLException, ExceptionPlageInexistante{
		//on recherche dans la BDD
		String sql = "SELECT  heure_debut, heure_fin, tranche FROM plage_horaire WHERE id_plagehoraire="+ id_plage;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last();
		int nbLigne = rs.getRow();
		if (nbLigne<=0) throw new ExceptionPlageInexistante("La plage n'existe pas");

		int hD = rs.getInt("heure_debut");
		int hF = rs.getInt("heure_fin");
		String t = rs.getString("tranche");

	
		return new PlageHoraire (hD, hF, getTranche(t));
	}

	public TRANCHE getTranche (String s) {
		if(s.equals("MATIN")) {
			return TRANCHE.MATIN;
		} else if (s.equals("SOIR")) {
			return TRANCHE.SOIR;
		} else if (s.equals("AM")) {
			return TRANCHE.AM;
		}
		return null;
	}
}
