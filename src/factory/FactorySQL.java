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

	public ResultSet getResultSet(String sql) {
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur éxécution requête");
		}
		return null;
	}
	
	public int executeUpdate(String sql) {
		PreparedStatement st;
		int result = 0;
		
		try {
			st = conn.prepareStatement(sql);
			result = st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("Erreur executeUpdate(sql)");
			e.printStackTrace();
		}
		
		return result;
	}
}
