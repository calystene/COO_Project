package metier;

import java.sql.SQLException;
import java.util.Date;

import data.horaire.TRANCHE;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;
import exception.ExceptionSalleInexistante;
import factory.FactoryReservation;

public class CreerReservation {
	
	public CreerReservation() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean verifPlageLibre(Date date, TRANCHE tranche, TYPE_SALLE typeS, int duree) throws SQLException, ExceptionPlageInexistante, ExceptionClientInexistant, ExceptionSalleInexistante {
		// Parcour les réservations et verifie si la duree fournit sur le créneaux horaire n'est pas déjà prit par une réservation confirmé.
		// Si on tombe sur une réservation non confirmé dont le délai de confirmation est dépassé, alors on considère cette réservation comme disponible et on return true
		// Si tout les paramètres sont ok, alors on return true
		// Sinon false
		
		// Si factorydate liste reservation leve une exception, alors tout les créneaux sont libres ce jours et on peut poser la réservation
		// sinon c'est qu'il y a des réservations, et à partir de la liste retourné, il faut vérifier si les plages sont disponibles
		
		
		FactoryReservation.getInstance().listeReservationDate(date);
		
		return false;
	}
}
