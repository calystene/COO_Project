package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import util.date.DateManager;
import data.Reservation;
import data.horaire.PlageHoraire;
import data.horaire.TRANCHE;
import data.salle.Salle;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientInexistant;
import exception.ExceptionCreneauNonDisponible;
import exception.ExceptionJourFerie;
import exception.ExceptionPlageInexistante;
import exception.ExceptionResaMultiImpossible;
import exception.ExceptionReservationExistante;
import exception.ExceptionSalleInexistante;
import factory.FactoryReservation;
import factory.FactorySalle;

public class CreerReservationMulti {


	public static Queue<PlageHoraire> verifPlagesLibres(Date date, TRANCHE tranche,
			TYPE_SALLE typeS, int duree, int nbSemaine) throws SQLException, ExceptionClientInexistant, ExceptionSalleInexistante, ExceptionCreneauNonDisponible, ExceptionJourFerie, ExceptionResaMultiImpossible {
		int i=nbSemaine;
		Date nextDate = date;
		Queue<PlageHoraire> listePlages = new LinkedList<PlageHoraire>();
		
		try {
		while(i--!=0) {
			PlageHoraire pl = CreerReservationAdmin.verifPlageLibre(nextDate, tranche, typeS, duree);
			listePlages.add(pl);
			nextDate = DateManager.addOneWeekFromDate(nextDate);
		}
		} catch (ExceptionPlageInexistante e) {
			throw new ExceptionResaMultiImpossible("Le créneaux pour la date " + DateManager.valueOf(nextDate) + " n'est pas disponible");
		}
		return listePlages;
	}
	
	/**
	 * Même principe que l'algo verifPlageLibre mais retourne une salle et non
	 * une plage
	 * 
	 * @param date
	 * @param tranche
	 * @param typeS
	 * @param duree
	 * @return
	 * @throws SQLException
	 * @throws ExceptionPlageInexistante
	 * @throws ExceptionClientInexistant
	 * @throws ExceptionSalleInexistante
	 * @throws ExceptionCreneauNonDisponible
	 */
	public static Salle getSalleLibre(Date date, TRANCHE tranche,
			TYPE_SALLE typeS, int duree) throws SQLException,
			ExceptionPlageInexistante, ExceptionClientInexistant,
			ExceptionSalleInexistante, ExceptionCreneauNonDisponible {

		ArrayList<Reservation> listeResa = new ArrayList<Reservation>();
		Stack<Reservation> stackReservation = new Stack<Reservation>();

		// Boolean servant à la vérification de l'existence d'une plage libre
		boolean check = true;
		Salle salleLibre = null;

		// On vérifie pour toute les salles
		ArrayList<Salle> listeSalle = new ArrayList<Salle>();
		listeSalle = FactorySalle.getInstance().listeSalleBDD();

		for (Salle s : listeSalle) {
			if (s.getTypeSalle().equals(typeS)) {
				check = true;
				salleLibre = s;
				listeResa = s.getReservation();

				// Si aucune réservation posé sur cette salle, alors les plages
				// sont disponibles
				if (listeResa.isEmpty()) {
					return s;
				}

				// Sinon pour chaque réservation de la salle
				for (Reservation r : listeResa) {
					// On prend celles à la même date
					if (r.getDateReservation().equals((date))) {
						// On prend celles au même Tranche horaire
						if (r.getPlage().getTranche().equals(tranche)) {
							stackReservation.push(r);
						}
					}
				}

				// Si la pile est vide pour la tranche horaire demandé, alors la
				// salle est libre
				if (stackReservation.empty()) {
					return salleLibre;
				}

				Reservation lastResa = stackReservation.peek();
				int hFinResa = lastResa.getPlage().getHeureFin() + duree;
				
				// On verifie si la duree demandé rentre sur le
				// créneaux demandé
				switch ((TRANCHE) tranche) {
				case MATIN:
					if (hFinResa > 13) {
						check = false;
					}
					break;
				case AM:
					if (hFinResa > 20) {
						check = false;
					}
					break;
				case SOIR:
					if (hFinResa > 24) {
						check = false;
					}
					break;
				}

				if (check) {
					return salleLibre;
				}

				while (!stackReservation.isEmpty()) {
					Reservation resaStack = stackReservation.pop();
					Date dateMaxValidite = DateManager
							.addOneWeekFromDate(resaStack
									.getDatePriseReservation());

					if ((DateManager.getDate().compareTo(dateMaxValidite) == 1)
							&& (resaStack.getEtatPaiement() == false)) {
						
						if (duree == resaStack.getDuree()) {
							FactoryReservation.getInstance().supprReservation(
									resaStack.hashCode()); // On supprime la
															// réservation hors
															// délais non
															// confirmée

							return resaStack.getSalle();
						} else {
							throw new ExceptionCreneauNonDisponible(
									"La durée de la nouvelle réservation doit être égale à celle supprimée");
						}

					}
				}

				stackReservation.clear();
			}
		}

		throw new ExceptionCreneauNonDisponible(
				"Le créneaux demandé est indisponible pour ces critères");
	}


	
	public static ArrayList<Reservation> creerReservationMulti(Date dateResa,
			Queue<PlageHoraire> filePlageH, int numeroC, String nomC, TYPE_SALLE typeS,
			int duree) throws ExceptionResaMultiImpossible,
			ExceptionReservationExistante, SQLException, ExceptionClientInexistant {

		Queue<PlageHoraire> copyFile = filePlageH;
		Date nextDate = dateResa;
		ArrayList<Reservation> listeResa = new ArrayList<Reservation>();
		Reservation r;

		try {
			// On parcours la file des plages horaires
			while(!copyFile.isEmpty()) {
				PlageHoraire ph = copyFile.poll();
				r = CreerReservationAdmin.creerReservation(nextDate, ph, numeroC, nomC, typeS,
						duree);
				listeResa.add(r);
				nextDate = DateManager.addOneWeekFromDate(nextDate);
			}
		} catch (SQLException | ExceptionClientInexistant
				| ExceptionPlageInexistante | ExceptionSalleInexistante
				| ExceptionReservationExistante e) {

		} catch (ExceptionCreneauNonDisponible e2) {
			throw new ExceptionResaMultiImpossible(
					"Impossible de réserver le : "
							+ DateManager.valueOf(nextDate));
		}

		return listeResa;
	}
}
