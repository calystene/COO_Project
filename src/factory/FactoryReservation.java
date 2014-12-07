package factory;

import java.util.ArrayList;
import java.util.Date;

import data.Client;
import data.Reservation;
import data.horaire.PlageHoraire;
import data.salle.Salle;

public class FactoryReservation {
	private ArrayList<Reservation> cacheReservation;
	private FactoryReservation singleton;
	
	private FactoryReservation () {
		cacheReservation = new ArrayList<Reservation>();
	}
	
	public FactoryReservation getInstance() {
		if(singleton==null)
			singleton = new FactoryReservation();
		
		return singleton;
	}
	
	public Reservation creerReservation(Date dPR, Date dR, ArrayList<PlageHoraire> plages, int prix, Client c, Salle s) {
		String sql = "INSERT INTO RESERVATION () VALUES ()";
		
		
		
		return null;
	}
	
	public Reservation rechercherReservation() {
		return null;
	}
	
	public void supprimerReservation() {
		
	}
}
