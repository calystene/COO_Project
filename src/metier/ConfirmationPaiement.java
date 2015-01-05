package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import data.Client;
import data.Reservation;
import data.forfait.Forfait;
import data.forfait.TYPE_FORFAIT;
import data.horaire.TRANCHE;
import exception.ExceptionForfaitExistant;
import exception.ExceptionReservationInexistante;
import factory.FactoryClient;
import factory.FactoryForfait;
import factory.FactorySQL;

public class ConfirmationPaiement {
	
	public static int RafraichirPrix(Reservation r) throws SQLException, ExceptionReservationInexistante {
		String sql = "SELECT prix FROM reservation WHERE id_reservation="+r.hashCode();
		ResultSet rs = FactorySQL.getInstance().getResultSet(sql);
		rs.last();
		int nbLigne = rs.getRow();
		if (nbLigne<=0) throw new ExceptionReservationInexistante("La Reservation n'existe pas");
		int prix = rs.getInt("prix");
		return prix;
	}

	public static void utilisationCarte(Reservation r) {
		int duree;
		int nbrH = r.getClient().getCarteFidelite().getNbHeureGratuite();
		float prixNew = 0;
		if (nbrH <= r.getDuree()){
			duree = r.getDuree()-nbrH;
			r.getClient().getCarteFidelite().setNbHeureGratuite(0);
			FactoryClient.getInstance().majClient(r.getClient());
		} else {
			duree = 0;
			int nbrHNew = nbrH - r.getDuree();
			r.getClient().getCarteFidelite().setNbHeureGratuite(nbrHNew);
			FactoryClient.getInstance().majClient(r.getClient());
		}	
		if (duree == 0){
			prixNew = 0;
		} else if (duree == 1) {
			prixNew = r.getSalle().getPrix1H();
		} else if (duree == 2) {
			prixNew = r.getSalle().getPrix2H();
		} else if (duree > 2) {
			int nbFois2H = (duree / 2);
			prixNew = (nbFois2H * r.getSalle().getPrix2H()) + r.getSalle().getPrix1H();
		}
		if (r.getPlage().getTranche().equals(TRANCHE.SOIR)) {
			prixNew *= 1.02;
		}
		
		String sql = "UPDATE reservation SET prix ="+prixNew+" where id_reservation="+r.hashCode();
		FactorySQL.getInstance().executeUpdate(sql);
		
	}

	public static void utilisationForfait(Reservation r, Forfait f) {
		int duree;
		int nbrH = f.getHeureDisponible();
		float prixNew = 0;
		if (nbrH <= r.getDuree()){
			duree = r.getDuree()-nbrH;
			f.setHeureDisponible(0);
			FactoryForfait.getInstance().majForfait(f);
		} else {
			duree = 0;
			int nbrHNew = nbrH - r.getDuree();
			f.setHeureDisponible(nbrHNew);
			FactoryForfait.getInstance().majForfait(f);
		}	
		if (duree == 0){
			prixNew = 0;
		} else if (duree == 1) {
			prixNew = r.getSalle().getPrix1H();
		} else if (duree == 2) {
			prixNew = r.getSalle().getPrix2H();
		} else if (duree > 2) {
			int nbFois2H = (duree / 2);
			prixNew = (nbFois2H * r.getSalle().getPrix2H()) + r.getSalle().getPrix1H();
		}
		if (r.getPlage().getTranche().equals(TRANCHE.SOIR)) {
			prixNew *= 1.02;
		}
		
		String sql = "UPDATE reservation SET prix ="+prixNew+" where id_reservation="+r.hashCode();
		FactorySQL.getInstance().executeUpdate(sql);
		
	}
}
