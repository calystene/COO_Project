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

public class CreerReservation {

	public CreerReservation() {
		// TODO Auto-generated constructor stub
	}

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
				break;
			case AM:
				hDebut = 13;
				hFin = hDebut + duree;
				break;
			case SOIR:
				hDebut = 20;
				hFin = hDebut + duree;
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
						break;
					case AM:
						hDebut = 13;
						hFin = hDebut + duree;
						break;
					case SOIR:
						hDebut = 20;
						hFin = hDebut + duree;
						break;
					}
					
					return FactoryPlageHoraire.getInstance().creerPlageHoraire(
							hDebut, hFin, tranche);
				}

				// Sinon pour chaque réservation de la salle
				for (Reservation r : listeResa) {
					// On prend celles à la même date
					if (r.getDateReservation().equals((date))) {
						// On prend celles au même Tranche horaire et on vérifie
						// que c'est le dernier de la liste
						if (r.getPlage().getTranche().equals(tranche)) {
							stackReservation.push(r);
						}
					}
				}
				System.out.println("HERE " + s.toString() + " " + stackReservation.size());
				if(stackReservation.empty()) {
					
					switch ((TRANCHE) tranche) {
					case MATIN:
						hDebut = 9;
						hFin = hDebut + duree;
						break;
					case AM:
						hDebut = 13;
						hFin = hDebut + duree;
						break;
					case SOIR:
						hDebut = 20;
						hFin = hDebut + duree;
						break;
					}
					
					return FactoryPlageHoraire.getInstance().creerPlageHoraire(
							hDebut, hFin, tranche);
				}
				
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
				
				System.out.println(s.toString());
				if (check) {
					return FactoryPlageHoraire.getInstance().creerPlageHoraire(hDebut,
							hFin, tranche);
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
		Salle salle = getSalleLibre(dateResa, plageH.getTranche(), typeS, duree);
		System.out.println(salle.toString());
		
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
