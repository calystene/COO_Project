package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import util.date.DateManager;
import data.Client;
import data.Reservation;
import data.forfait.Forfait;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;
import exception.ExceptionReservationInexistante;
import exception.ExceptionSalleInexistante;
import factory.FactoryClient;
import factory.FactoryForfait;
import factory.FactoryReservation;

public class RechercheClient {

	public static Client rechercherClient(String nom, int numero)
			throws SQLException, ExceptionClientInexistant {
		return FactoryClient.getInstance().rechercherClient(nom, numero);
	}
	
	public static Object[][] VisualiserReserClient(Client c)
			throws SQLException, ExceptionPlageInexistante,
			ExceptionSalleInexistante, ExceptionReservationInexistante {
		ArrayList<Reservation> listeRes = FactoryReservation.getInstance()
				.rechercherByClient(c);

		// On converti le résultat en tableau à 2 dimensions pour qu'il soit
		// lisible par le TableAbstractModel
		Object[][] tabResult = new Object[listeRes.size()][8]; // Ici le 9
																// correspond
																// aux 9
																// colonnes du
																// tableaux
																// planning

		int i = 0;
		for (Reservation r : listeRes) {
			tabResult[i][0] = r.hashCode();
			tabResult[i][1] = r.getDatePriseReservation();
			tabResult[i][2] = r.getDateReservation();
			tabResult[i][3] = r.getSalle().getNom();
			tabResult[i][4] = r.getPlage().getHeureDebut();
			tabResult[i][5] = r.getPlage().getHeureFin();
			tabResult[i][6] = r.getPrix();

			Date dateMaxResa = DateManager.addOneWeekFromDate(r
					.getDatePriseReservation());
			if (!r.getEtatPaiement()
					&& DateManager.getDate().compareTo(dateMaxResa) == 1) {
				tabResult[i][7] = "Hors-délais";
			} else if (!r.getEtatPaiement()) {
				tabResult[i][7] = "Non";
			} else {
				tabResult[i][7] = "Oui";
			}
			i++;
		}
		return tabResult;
	}

	public static Object[][] VisualiserForfaitClient(Client c)
			throws SQLException {
		ArrayList<Forfait> listeF = FactoryForfait.getInstance()
				.rechercherByClient(c);

		// On converti le résultat en tableau à 2 dimensions pour qu'il soit
		// lisible par le TableAbstractModel
		Object[][] tabResult = new Object[listeF.size()][4]; // Ici le 4
																// correspond
																// aux 4
																// colonnes du
																// tableaux
																// planning

		int i = 0;
		for (Forfait r : listeF) {
			tabResult[i][0] = r.getNumero();
			;
			tabResult[i][1] = r.getHeureDisponible();
			tabResult[i][2] = DateManager.valueOf(r.getDateFinValidite());
			tabResult[i][3] = r.getType();
			i++;
		}
		return tabResult;
	}

	public static void supprReservation(int idReservation) {
		FactoryReservation.getInstance().supprReservation(idReservation);
	}

	public static void ajouterPointFidelite(String nom, int numero, int points)
			throws SQLException, ExceptionClientInexistant {
		Client c = FactoryClient.getInstance().rechercherClient(nom, numero);

		int nbPoints = c.getCarteFidelite().getNbPoint();

		nbPoints += points;

		if (nbPoints == 150) {
			c.getCarteFidelite().setNbPoint(0);

			int nbHeureGratuite = c.getCarteFidelite().getNbHeureGratuite();
			nbHeureGratuite += 1;
			c.getCarteFidelite().setNbHeureGratuite(nbHeureGratuite);
		} else {
			c.getCarteFidelite().setNbPoint(nbPoints);
		}

		FactoryClient.getInstance().majClient(c);
	}

	public static void majEtatPaiement(Reservation r) {
		FactoryReservation.getInstance().majReservation(r);
	}


	public static Reservation rechercheReservation(int idReservation)
			throws SQLException, ExceptionReservationInexistante,
			ExceptionPlageInexistante, ExceptionClientInexistant,
			ExceptionSalleInexistante {
		return FactoryReservation.getInstance().rechercheReservation(
				idReservation);
	}
}
