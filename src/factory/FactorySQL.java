package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FactorySQL {
	private Connection conn;
	private static FactorySQL singleton;

	private FactorySQL() { 
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erreur Class.forName");
		}
		
		try {
			conn = DriverManager
					.getConnection(
							"jdbc:hsqldb:file:bdd/data_base;shutdown=true",
							"coo", "coo");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur DriverManager.getConnection");
		}
	}
	
	static public FactorySQL getInstance() {
		if (singleton != null) {
			return singleton;
		}
		
		singleton = new FactorySQL();
		return singleton;
	}
	
	
	/**
	 * Permet d'éxécuter une requête et de récupérer son résultat dans un ResultSet
	 * @param sql La requête à éxécuté
	 * @return Le result
	 */
	public ResultSet getResultSet(String sql) {
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery();
	
//			conn.close();
//			singleton=null;
//			System.out.println("Fermeture connexion");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur éxécution requête");
		}
		
		return null;
	}
	
	
	/** 
	 * Permet d'éxécuter une requête de mise à jour
	 * @param sql La requête à éxécuter
	 * @return Le nombre de ligne modifiées
	 */
	public int executeUpdate(String sql) {
		PreparedStatement st;
		int result = 0;
		
		try {
			st = conn.prepareStatement(sql);
			result = st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erreur executeUpdate(sql) sur la requête : " + sql);
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * Permet de fermer la connection à la base
	 * Penser à le faire à la fin du programme sinon le commit ne se fait pas
	 */
	public void shutdown() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
