package factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.salle.Salle;
import data.salle.TYPE_SALLE;
import exception.ExceptionSalleExistante;
import exception.ExceptionSalleInexistante;

public class FactorySalle {
	
	private static FactorySalle singleton;
	
	private HashMap<Integer, Salle> cacheSalle;
	
	private FactorySalle() {
		cacheSalle = new HashMap<Integer, Salle>();
	}

	public static FactorySalle getInstance() {
		if (singleton == null)
			singleton = new FactorySalle();

		return singleton;
	}
	
	/**
	 * Permet de créer une salle dans le cache et dans la BDD
	 * @param nom
	 * @param prix1H
	 * @param prix2H
	 * @param typeSalle
	 * @return la salle créée
	 * @throws ExceptionSalleExistante
	 * @throws SQLException
	 */
	public Salle creerSalle (String nom, int prix1H, int prix2H, TYPE_SALLE typeSalle) throws ExceptionSalleExistante, SQLException{
		int idSalle = Math.abs(nom.hashCode());
		Salle s = new Salle (nom, prix1H, prix2H, typeSalle);
		
		// On vérifie l'existence de la salle dans le cache
				if (cacheSalle.containsKey(idSalle))
					throw new ExceptionSalleExistante("La salle existe déjà");

		// On vérifie si la salle existe en BDD
		String sql = "SELECT COUNT(id_salle) as id_salle FROM SALLE WHERE id_salle="+ idSalle;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			if (rs.getInt("id_salle") >= 1)
				throw new ExceptionSalleExistante("La salle existe déjà");
		}
		
		// On l'ajoute au cache et à la BDD
		cacheSalle.put(idSalle, s);

		sql = "INSERT INTO SALLE (id_salle, nom, prix1H, prix2H, type_Salle)"
				+ " VALUES ("
				+ idSalle
				+ ", '"
				+ nom
				+ "', "
				+ prix1H
				+ ", "
				+ prix2H
				+ ", '"
				+ typeSalle.toString() + "')";
		
		FactorySQL.getInstance().executeUpdate(sql);
		
		//On retourne la salle
		return s;
	}
	
	/**
	 * Fonction qui permet de rechercher une salle grace à son nom
	 * @param nom
	 * @return la salle recherchée
	 * @throws ExceptionSalleInexistante
	 * @throws SQLException
	 */
	public Salle rechercheSalle(String nom) throws ExceptionSalleInexistante, SQLException{
		
		//On recherche dans le cache 
		Salle s = cacheSalle.get(nom.hashCode());
		if (s!=null){
			return s;		
		} else {
		//Sinon on recherche dans la BDD
		String sql = "SELECT id_salle, nom, prix1H, prix2H, type_salle FROM SALLE WHERE nom='"+ nom +"'";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last();
		int nbLigne = rs.getRow();
		if (nbLigne<=0) throw new ExceptionSalleInexistante("La salle n'existe pas");

		int prix1H = rs.getInt("prix1H");
		int prix2H = rs.getInt("prix2H");
		String typeSalle = rs.getString("type_salle");
		
	
		return new Salle(nom, prix1H, prix2H, getTypeSalle(typeSalle));
		}
	}
	
	/**
	 * Permet de supprimer une salle de la BDD et du cache
	 * @param nom
	 */
	public void supprSalle(String nom){
		//Suppression dans le cache
		cacheSalle.remove(nom.hashCode());
		//Suppression dans la BDD
		String sql = "DELETE FROM SALLE WHERE nom='" + nom +"'" ;
		FactorySQL.getInstance().executeUpdate(sql);
	}
	
	/**
	 *  Fonction permettant de transformer un string en un objet TYPE_SALLE
	 * @param s
	 * @return TYPE_SALLE
	 */
	private TYPE_SALLE getTypeSalle(String s) {
		if(s.equals("PETITE_SALLE")) {
			return TYPE_SALLE.PETITE_SALLE;
		} else if (s.equals("GRANDE_SALLE")) {
			return TYPE_SALLE.GRANDE_SALLE;
		} else if (s.equals("SPECIFIQUE_SALLE")) {
			return TYPE_SALLE.SPECIFIQUE_SALLE;
		}
		return null;
	}
	/**
	 * 	Permet de retourner la liste des salle dans le cache
	 * @return liste des salle du cache
	 */
	public Iterator<Salle> listeSallesCache(){
		return cacheSalle.values().iterator();	
	}
	
	/**
	 * Permet de retourner la liste des salle dans la BDD
	 * @return  liste des salle de la BDD
	 * @throws SQLException 
	 */
	public ArrayList<Salle> listeSalleBDD() throws SQLException{
		ArrayList<Salle> lesSalles = new ArrayList<Salle>();
		String sql = "SELECT id_salle, nom, prix1H, prix2H, type_salle FROM SALLE";
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);

		while (rs.next()) {
			String nom = rs.getString("nom");
			int prix1H = rs.getInt("prix1H");
			int prix2H = rs.getInt("prix2H");
			String type = rs.getString("type_salle");

			Salle s = new Salle(nom, prix1H, prix2H, getTypeSalle(type));
			lesSalles.add(s);
		}
		return lesSalles;
	}

	public Salle rechercheSalle(int id) throws SQLException, ExceptionSalleInexistante {
		//On recherche dans le cache 
		Salle s = cacheSalle.get(id);
		if (s!=null){
			return s;		
		} else {
		//Sinon on recherche dans la BDD
		String sql = "SELECT id_salle, nom, prix1H, prix2H, type_salle FROM SALLE WHERE id_salle="+ id ;
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last();
		int nbLigne = rs.getRow();
		if (nbLigne<=0) throw new ExceptionSalleInexistante("La salle n'existe pas");

		String nom = rs.getString("nom");
		int prix1H = rs.getInt("prix1H");
		int prix2H = rs.getInt("prix2H");
		String typeSalle = rs.getString("type_salle");
		
	
		return new Salle(nom, prix1H, prix2H, getTypeSalle(typeSalle));
		}
	}
}
	