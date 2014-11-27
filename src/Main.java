import java.util.Date;
import java.util.GregorianCalendar;

import util.date.DateManager;


public class Main {

	public static void main(String[] args) {
		Date date = DateManager.getInstance().getDate();
		Date dateSQL = DateManager.getInstance().getDateSQL();
		
		System.out.println(DateManager.getInstance().dateToString());
		System.out.println(DateManager.getInstance().valueOf(dateSQL));
		System.out.println(DateManager.getInstance().valueOf(DateManager.getInstance().addMonth(5)));
		System.out.println(DateManager.getInstance().valueOf(DateManager.getInstance().addMonth(dateSQL,5)));
	
		Date ferie = new Date(2015,0,1);
		GregorianCalendar paque = new GregorianCalendar(2015,3,6,12,30);
		GregorianCalendar gc = new GregorianCalendar(2015,0,1,9,30);
		
		System.out.println(DateManager.getInstance().jourOuvrable(gc.getTime()));
		System.out.println(DateManager.getInstance().jourOuvrable(paque.getTime()));
		System.out.println(DateManager.getInstance().jourOuvrable(date));
	}

}
