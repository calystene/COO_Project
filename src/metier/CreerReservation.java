package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

				// Si aucune réservation posé sur cette salle, alors les plages
				// sont disponibles
				if (listeResa.isEmpty()) {
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
							int hFinResa = r.getPlage().getHeureFin() + duree;

							// On verifie si la duree demandé rentre sur le
							// créneaux demandé
							switch ((TRANCHE) tranche) {
							case MATIN:
								if (hFinResa > 13) {
									check = false;
								} else {
									hDebut = r.getPlage().getHeureFin();
									hFin = hDebut + duree;
								}
								break;
							case AM:
								if (hFinResa > 20) {
									check = false;
								} else {
									hDebut = r.getPlage().getHeureFin();
									hFin = hDebut + duree;
								}
								break;
							case SOIR:
								if (hFinResa > 24) {
									check = false;
								} else {
									hDebut = r.getPlage().getHeureFin();
									hFin = hDebut + duree;
								}
								break;
							}
						}
					}
				}
			}
		}
		if (check) {
			return FactoryPlageHoraire.getInstance().creerPlageHoraire(hDebut,
					hFin, tranche);
		} else {
			throw new ExceptionCreneauNonDisponible(
					"Le créneaux demandé est indisponible pour ces critères");
		}
	}

	public static Salle getSalleLibre(Date date, TRANCHE tranche,
			TYPE_SALLE typeS, int duree) throws SQLException,
			ExceptionPlageInexistante, ExceptionClientInexistant,
			ExceptionSalleInexistante, ExceptionCreneauNonDisponible {

		ArrayList<Reservation> listeResa = new ArrayList<Reservation>();

		// Boolean servant à la vérification de l'existence d'une plage libre
		boolean check = true;
		Salle salleLibre = null;

		// On vérifie pour toute les salles
		ArrayList<Salle> listeSalle = new ArrayList<Salle>();
		listeSalle = FactorySalle.getInstance().listeSalleBDD();

		for (Salle s : listeSalle) {
			if (s.getTypeSalle().equals(typeS)) {
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
						// On prend celles au même Tranche horaire et on vérifie
						// que c'est le dernier de la liste
						if (r.getPlage().getTranche().equals(tranche)) {
							int hFinResa = r.getPlage().getHeureFin() + duree;

							// On verifie si la duree demandé rentre sur le
							// créneaux demandé
							switch ((TRANCHE) tranche) {
							case MATIN:
								if (hFinResa > 13) {
									check = false;
								} else {
									salleLibre = s;
								}
								break;
							case AM:
								if (hFinResa > 20) {
									check = false;
								} else {
									salleLibre = s;
								}
								break;
							case SOIR:
								if (hFinResa > 24) {
									check = false;
								} else {
									salleLibre = s;
								}
								break;
							}
						}
					}
				}
			}
		}
		if (check) {
			return salleLibre;
		} else {
			throw new ExceptionCreneauNonDisponible(
					"Le créneaux demandé est indisponible pour ces critères");
		}
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
	 */
	public static Reservation creerReservation(Date dateResa,
			PlageHoraire plageH, int numeroC, String nomC, TYPE_SALLE typeS,
			int duree) throws SQLException, ExceptionClientInexistant,
			ExceptionPlageInexistante, ExceptionSalleInexistante,
			ExceptionCreneauNonDisponible {

		Client c = FactoryClient.getInstance().rechercherClient(nomC, numeroC);
		Date datePriseResa = DateManager.getDate();
		Salle salle = getSalleLibre(dateResa, plageH.getTranche(), typeS, duree);

		
		// Calul du prix
		int prix  = 0;
		if(duree==1) {
			prix = salle.getPrix1H();
		} else if(duree==2) {
			prix = salle.getPrix2H();
		} else if(duree > 2) {
			int nbFois2H = duree / 2;
			prix = (nbFois2H * salle.getPrix2H()) + salle.getPrix1H();
		}
		
		if(plageH.getTranche().equals(TRANCHE.SOIR)) {
			prix += prix * 1.02;
		}
		
		return null;
	}
}
