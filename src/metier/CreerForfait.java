package metier;

import java.sql.SQLException;

import data.Client;
import data.forfait.Forfait;
import data.forfait.TYPE_FORFAIT;
import exception.ExceptionForfaitExistant;
import factory.FactoryForfait;

public class CreerForfait {
	
	public static Forfait CreerForfaitClient(Client c, TYPE_FORFAIT t ) throws ExceptionForfaitExistant, SQLException {
		return FactoryForfait.getInstance().creerForfait(c, t);
	}
}
