package metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.Reservation;
import data.salle.Salle;
import data.salle.TYPE_SALLE;
import exception.ExceptionClientInexistant;
import exception.ExceptionPlageInexistante;
import factory.FactorySalle;

public class VisualiserPlanning {
	public VisualiserPlanning() {
		// TODO Auto-generated constructor stub
	}
	
	public static Object[][] planningSalleDate(TYPE_SALLE ts, Date d) throws SQLException, ExceptionPlageInexistante, ExceptionClientInexistant {
		ArrayList<Salle> listeSalle = FactorySalle.getInstance().listeSalleBDD();
		ArrayList<Reservation> listeResaDateSalle = new ArrayList<Reservation>();
		
		// On parcours la liste des salles
		for(Salle s : listeSalle) {
			if(s.getTypeSalle().equals(ts)) { // Si le type correspond au type demandé
				ArrayList<Reservation> listeResa = s.getReservation(); // On récupérère la liste des reservations de ce type de salle
				
				// On parcours les réservations de cette salle
				for(Reservation r : listeResa) {
					if(r.getDateReservation().equals(d)) { // et on ajoute à l'ArrayList celle qui dont la date nous intéresse
						listeResaDateSalle.add(r);
					}
				}
			}
		}
		
		// On converti le résultat en tableau à 2 dimensions pour qu'il soit lisible par le TableAbstractModel
		Object[][] tabResult = new Object[listeResaDateSalle.size()][6]; // Ici le 5 correspond aux 5 colonnes du tableaux planning
		
		int i =0;
		for(Reservation r : listeResaDateSalle) {
			tabResult[i][0] = r.getSalle().getNom(); // le nom de la salle
			tabResult[i][1] = r.getPlage().getTranche(); // la tranche horaire
			tabResult[i][2] = r.getPlage().getHeureDebut(); // L'heure de début de la plage
			tabResult[i][3] = r.getPlage().getHeureFin(); // L'heure de fin de la plage
			tabResult[i][4] = r.getEtatPaiement(); // L'état de la réservation
			tabResult[i][5] = r.getClient().getNom(); // Le nom du client
			i++;
		}
		return tabResult;
	}
}
