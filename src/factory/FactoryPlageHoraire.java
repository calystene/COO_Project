package factory;

import data.horaire.PlageHoraire;
import data.horaire.TRANCHE;
import exception.ExceptionPlageIncorrect;

public class FactoryPlageHoraire {
	private FactoryPlageHoraire singleton;
	
	private FactoryPlageHoraire () {
	}
	
	public FactoryPlageHoraire getInstance() {
		if (singleton==null) 
			singleton = new FactoryPlageHoraire();
		
		return singleton;
	}
	
	
	public PlageHoraire creerPlageHoraire (int heureD, int heureF, TRANCHE t) throws ExceptionPlageIncorrect {
		
		
		PlageHoraire ph = new PlageHoraire(heureD, heureF, t);
		return ph;
	}
}
