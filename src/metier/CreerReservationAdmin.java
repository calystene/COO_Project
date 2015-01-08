package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import util.date.DateManager;
import data.Client;
import data.Reservation;
import data.horaire.PlageHoraire;
import data.horaire.TRANCHE;
import data.salle.Salle;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientInexistant;
import exception.ExceptionCreneauNonDisponible;
import exception.ExceptionJourFerie;
import exception.ExceptionPlageInexistante;
import exception.ExceptionReservationExistante;
import exception.ExceptionSalleInexistante;
import factory.FactoryClient;
import factory.FactoryPlageHoraire;
import factory.FactoryReservation;
import factory.FactorySalle;

public class CreerReservationAdmin {
	public static PlageHoraire verifPlageLibre(Date date, TRANCHE tranche,
			TYPE_SALLE typeS, int duree) throws SQLException,
			ExceptionPlageInexistante, ExceptionClientInexistant,
			ExceptionSalleInexistante, ExceptionCreneauNonDisponible, ExceptionJourFerie {

		if(!DateManager.jourOuvrable(date)) {
			throw new ExceptionJourFerie("La date choisie est un jour férié");
		}
		
		Stack<Reservation> stackReservation = new Stack<Reservation>();
		ArrayList<Reservation> listeResa = new ArrayList<Reservation>();
		int hDebut = 0;
		int hFin = 0;

		java.sql.Date dateSQL = DateManager.dateToSQL(date);
		listeResa = FactoryReservation.getInstance().listeReservationDate(
				dateSQL);

		// Si aucune réservation posé à cette date, alors les plages sont
		// disponibles
		if (listeResa.isEmpty()) {
			switch ((TRANCHE) tranche) {
			case MATIN:
				hDebut = 9;
				hFin = hDebut + duree;
				
				if(hFin>13) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
				break;
			case AM:
				hDebut = 13;
				hFin = hDebut + duree;
				
				if(hFin>20) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
				break;
			case SOIR:
				hDebut = 20;
				hFin = hDebut + duree;
				
				if(hFin>24) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
				break;
			}

			return FactoryPlageHoraire.getInstance().creerPlageHoraire(hDebut,
					hFin, tranche);
		}

		// Boolean servant à la vérification de l'existence d'une plage libre
		boolean check = true;

		// On vérifie pour toute les salles
		ArrayList<Salle> listeSalle = new ArrayList<Salle>();
		listeSalle = FactorySalle.getInstance().listeSalleBDD();

		for (Salle s : listeSalle) {
			if (s.getTypeSalle().equals(typeS)) {
				listeResa = s.getReservation();
				check = true;
				
				// Si aucune réservation posé sur cette salle, alors les plages
				// sont disponibles
				if (listeResa.isEmpty()) {
					switch ((TRANCHE) tranche) {
					case MATIN:
						hDebut = 9;
						hFin = hDebut + duree;
						
						if(hFin>13) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
						break;
					case AM:
						hDebut = 13;
						hFin = hDebut + duree;
						
						if(hFin>20) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
						break;
					case SOIR:
						hDebut = 20;
						hFin = hDebut + duree;
						
						if(hFin>24) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
						break;
					}
					
					return FactoryPlageHoraire.getInstance().creerPlageHoraire(
							hDebut, hFin, tranche);
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

				// SI la tranche est vide c'est qu'il y a aucune réservation, on retourne donc la tranche horaire pour la nouvelle réservation
				if(stackReservation.empty()) {
					switch ((TRANCHE) tranche) {
					case MATIN:
						hDebut = 9;
						hFin = hDebut + duree;
						
						if(hFin>13) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
						break;
					case AM:
						hDebut = 13;
						hFin = hDebut + duree;
						
						if(hFin>20) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
						break;
					case SOIR:
						hDebut = 20;
						hFin = hDebut + duree;
						
						if(hFin>24) throw new ExceptionCreneauNonDisponible("Plage horaire illegal");
						break;
					}
					
					return FactoryPlageHoraire.getInstance().creerPlageHoraire(
							hDebut, hFin, tranche);
				}
				
			
				
				/** On vérifie que l'on ne puisse pas poser une réservation de façon normal (auto) **/
				Reservation lastResa = stackReservation.peek();
				int hFinResa = lastResa.getPlage().getHeureFin() + duree;
				
				// On verifie si la duree demandé rentre sur le
				// créneaux demandé
				switch ((TRANCHE) tranche) {
				case MATIN:
					if (hFinResa > 13) {
						check = false;
					} else {
						hDebut = lastResa.getPlage().getHeureFin();
						hFin = hDebut + duree;
					}
					break;
				case AM:
					if (hFinResa > 20) {
						check = false;
					} else {
						hDebut = lastResa.getPlage().getHeureFin();
						hFin = hDebut + duree;
					}
					break;
				case SOIR:
					if (hFinResa > 24) {
						check = false;
					} else {
						hDebut = lastResa.getPlage().getHeureFin();
						hFin = hDebut + duree;
					}
					break;
				}
				

				if (check) {
					return FactoryPlageHoraire.getInstance().creerPlageHoraire(hDebut,
							hFin, tranche);
				}
				
				
				/** Si la réservation n'est pas possible de façon auto, alors on dépile jusqu'à temps de voir si une réservation est hors délais de confirmation et correspond à la durée
				 * demandé **/
				while(!stackReservation.isEmpty()) {
					Reservation resaStack = stackReservation.pop();
					Date dateMaxValidite = DateManager.addOneWeekFromDate(resaStack.getDatePriseReservation());
					
					if((DateManager.getDate().compareTo(dateMaxValidite) == 1) && (resaStack.getEtatPaiement() == false)) {
						
						if(duree==resaStack.getDuree()) {
							hDebut = resaStack.getPlage().getHeureDebut();
							hFin = resaStack.getPlage().getHeureFin();
							
							//FactoryReservation.getInstance().supprReservation(resaStack.hashCode()); // On supprime la réservation hors délais non confirmée
							
							return FactoryPlageHoraire.getInstance().creerPlageHoraire(hDebut, hFin, tranche);
						} else {
							throw new ExceptionCreneauNonDisponible("La durée de la nouvelle réservation doit être égale à celle supprimée");
						}
						
					}
				}
				
				stackReservation.clear();
			}
		}	

		// Si l'algo n'a pas retourné de créneau avant cette étape, alors aucun créneaux n'est disponible
		throw new ExceptionCreneauNonDisponible(
				"Le créneaux demandé est indisponible pour ces critères");
	}

	
	/** Même principe que l'algo verifPlageLibre mais retourne une salle et non une plage
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
				
				// Si la pile est vide pour la tranche horaire demandé, alors la salle est libre
				if(stackReservation.empty()) {
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
				
				
				while(!stackReservation.isEmpty()) {
					Reservation resaStack = stackReservation.pop();
					Date dateMaxValidite = DateManager.addOneWeekFromDate(resaStack.getDatePriseReservation());
					
					if((DateManager.getDate().compareTo(dateMaxValidite) == 1) && (resaStack.getEtatPaiement() == false)) {
						
						if(duree==resaStack.getDuree()) {							
							FactoryReservation.getInstance().supprReservation(resaStack.hashCode()); // On supprime la réservation hors délais non confirmée
							
							return resaStack.getSalle();
						} else {
							throw new ExceptionCreneauNonDisponible("La durée de la nouvelle réservation doit être égale à celle supprimée");
						}
						
					}
				}
				
				stackReservation.clear();
			}
		}
		
		
		throw new ExceptionCreneauNonDisponible(
				"Le créneaux demandé est indisponible pour ces critères");
	}

	/**
	 * Permet de créer un réservation pour un client donnée, une salle donnée et
	 * une plageHoraire donnée
	 * 
	 * @param plageH
	 * @return
	 * @throws ExceptionClientInexistant
	 * @throws SQLException
	 * @throws ExceptionCreneauNonDisponible
	 * @throws ExceptionSalleInexistante
	 * @throws ExceptionPlageInexistante
	 * @throws ExceptionReservationExistante 
	 */
	public static Reservation creerReservation(Date dateResa,
			PlageHoraire plageH, int numeroC, String nomC, TYPE_SALLE typeS,
			int duree) throws SQLException, ExceptionClientInexistant,
			ExceptionPlageInexistante, ExceptionSalleInexistante,
			ExceptionCreneauNonDisponible, ExceptionReservationExistante {

		Client c = FactoryClient.getInstance().rechercherClient(nomC, numeroC);
		java.sql.Date datePriseResaSQL = DateManager.dateToSQL(DateManager.getDate());
		java.sql.Date dateResaSQL = DateManager.dateToSQL(dateResa);
		Salle salle = getSalleLibre(dateResa, plageH.getTranche(), typeS, duree); // La suppression de la réservation hors délais non confirmé se fait dans cette méthode
		
		// Calul du prix
		float prix  = 0;
		if(duree==1) {
			prix = salle.getPrix1H();
		} else if(duree==2) {
			prix = salle.getPrix2H();
		} else if(duree > 2) {
			int nbFois2H = (duree / 2);
			prix = (nbFois2H * salle.getPrix2H()) + salle.getPrix1H();
		}
		
		if(plageH.getTranche().equals(TRANCHE.SOIR)) {
			prix *= 1.02;
		}
		
		// Ajout des points de fidelité au client 
		RechercheClient.ajouterPointFidelite(c.getNom(), c.getNumero(), 5);
		
		Reservation r = FactoryReservation.getInstance().creerReservation(datePriseResaSQL, dateResaSQL, plageH, prix, c, salle, duree);
		return r;
	}
	
}
