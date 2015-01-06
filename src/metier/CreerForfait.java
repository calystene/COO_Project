package metier;

import java.sql.SQLException;

import data.Client;
import data.forfait.Forfait;
import data.forfait.TYPE_FORFAIT;
import exception.ExceptionClientInexistant;
import exception.ExceptionForfaitExistant;
import exception.ExceptionForfaitInexistant;
import factory.FactoryForfait;

public class CreerForfait {

	public static Forfait CreerForfaitClient(Client c, TYPE_FORFAIT t)
			throws ExceptionForfaitExistant, SQLException,
			ExceptionForfaitInexistant, ExceptionClientInexistant {
		int idForfait = Math.abs(c.hashCode() + t.toString().hashCode());

		Forfait f = FactoryForfait.getInstance().rechercherForfait(idForfait);
		if (f.getHeureDisponible() == 0) {
			if (t.equals(TYPE_FORFAIT.A_GRANDE)
					|| t.equals(TYPE_FORFAIT.A_PETITE)) {
				f.setHeureDisponible(12);
			} else {
				f.setHeureDisponible(24);
			}
			
			FactoryForfait.getInstance().majForfait(f);
			return f;
		} else {
			return FactoryForfait.getInstance().creerForfait(c, t);
		}
	}
}
