package factory;

import java.util.ArrayList;
import java.util.HashMap;

import data.Reservation;
import data.horaire.PlageHoraire;


public class FactoryReserPlageH {
	private HashMap<Integer, ArrayList<PlageHoraire>> cacheReserPlageH;
	private FactoryReserPlageH singleton;
	
	private FactoryReserPlageH() {
		cacheReserPlageH = new HashMap<Integer, ArrayList<PlageHoraire>>();
	} 
	
	public FactoryReserPlageH getInstance() {
		if(singleton==null) 
			singleton = new FactoryReserPlageH();
		
		return singleton;
	}
	
	public void creerReserPlageH(Reservation res, ArrayList<PlageHoraire> plages) {
		
		/**
		 * Principe : A ce stade la réservation existe en base: On ajoute successivement les plages à la table RESER_PLAGEHORAIRE
		 * en incrémentant de 1 à x le numéro des plages (et de sorte à ce qu'elles soient dans l'ordre)
		 * Voir pour peut être implenté une FILE à la place de l'ArrayL.ist
		 */
		
		
		cacheReserPlageH.put(res.hashCode(), plages);
		
	}
	
}
