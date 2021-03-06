package util.date;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateManager {
	
	private DateManager() {	}
	
	/**
	 * Retourne la date courante en String sous l'affichage : 'jj mmm. aaaa'
	 * 
	 * @return la date courante
	 */
	public static String dateToString() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
				Locale.FRANCE);
		return df.format(new java.util.Date());
	}

	
	
	/**
	 * Retourne la date courante au format SQL en String
	 * 
	 * @return la date courante au format SQL
	 */
	static public String dateSQLToString() {
		java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
		return d.toString();
	}

	
	/**
	 * Converti une util.Date en sql.Date
	 * @param pdate
	 * @return
	 */
	static public java.sql.Date dateToSQL (Date pdate) {
		java.sql.Date d = new java.sql.Date(pdate.getTime());
		return d;
	}
	
	
	/**
	 * Converti une sql.Date en util.Date
	 * @param d
	 * @return
	 */
	static public java.util.Date sqlToDate (java.sql.Date d) {
		return new java.util.Date(d.getTime());
	}
	
	
	/**
	 * Retourne l'objet de la date courante
	 * 
	 * @return la date courante
	 */
	public static Date getDate() {
		return new Date();
	}

	
	
	/**
	 * Retourne l'objet de la date courante SQL
	 * 
	 * @return
	 */
	public static java.sql.Date getDateSQL() {
		java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
		return d;
	}
	
	
	
	/**
	 * Affiche la date fournit en paramètre sous format texte
	 * 
	 * @param d
	 *            La date à transformer
	 * @return la date au format texte
	 */
	public static String valueOf(Date d) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
				Locale.FRANCE);
		return df.format(d);
	}

	
	
	/**
	 * Retourne la date courante avec le nombre de mois que l'on souhaite
	 * ajouter
	 * 
	 * @param month
	 *            le nombre de mois à ajouter
	 * @return la nouvelle date
	 */
	static public Date addMonthFromToday(int month) {
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	
	
	/**
	 * Retourne la date fournit en paramètre avec le nombre de mois que l'on
	 * souhaite ajouter
	 * 
	 * @param d
	 *            L'ancienne date
	 * @param month
	 *            le nombre de mois à ajouter
	 * @return la nouvelle date
	 */
	static public Date addMonthFromDate(Date d, int month) {
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	
	/**
	 * Ajoute une semaine à la date fournit en paramètre
	 * @param d la date initial
	 * @return La nouvelle date
	 */
	static public Date addOneWeekFromDate(Date d) {
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, 7);
		return calendar.getTime();
	}
	
	
	/**
	 * Indique si la date est un jour ouvrable ou non
	 * 
	 * @param date
	 *            La date à vérifier
	 * @return True si la date n'est pas un jour férié, sinon False
	 */
	static public boolean jourOuvrable(Date date) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date);
		int dateMonth = c1.get(Calendar.MONTH);
		int dateDay = c1.get(Calendar.DAY_OF_MONTH);
		int dMonth;
		int dDay;

		ArrayList<Date> datesFeries = getJourFeries(c1.get(Calendar.YEAR));

		for (Date d : datesFeries) {
			c2.setTime(d);
			dMonth = c2.get(Calendar.MONTH);
			dDay = c2.get(Calendar.DAY_OF_MONTH);

			if ((dateMonth == dMonth) && (dateDay == dDay))
				return false;
		}
		return true;
	}

	
	
	
	/**
	 * Retourne une liste de date contenant les jours férié
	 * 
	 * @param annee
	 * @return La liste des jours férié
	 */
	static public ArrayList<Date> getJourFeries(int annee) {
		ArrayList<Date> datesFeries = new ArrayList<Date>();

		// Jour de l'an
		GregorianCalendar jourAn = new GregorianCalendar(annee, 0, 1);
		datesFeries.add(jourAn.getTime());

		// Lundi de pacques
		GregorianCalendar pacques = calculLundiPacques(annee);
		datesFeries.add(pacques.getTime());

		// Fete du travail
		GregorianCalendar premierMai = new GregorianCalendar(annee, 4, 1);
		datesFeries.add(premierMai.getTime());

		// 8 mai
		GregorianCalendar huitMai = new GregorianCalendar(annee, 4, 8);
		datesFeries.add(huitMai.getTime());

		// Ascension (= pâques + 38 jours)
		GregorianCalendar ascension = new GregorianCalendar(annee,
				pacques.get(GregorianCalendar.MONTH),
				pacques.get(GregorianCalendar.DAY_OF_MONTH));
		ascension.add(GregorianCalendar.DAY_OF_MONTH, 38);
		datesFeries.add(ascension.getTime());

		// Pentecôte (= pâques + 49 jours)
		GregorianCalendar pentecote = new GregorianCalendar(annee,
				pacques.get(GregorianCalendar.MONTH),
				pacques.get(GregorianCalendar.DAY_OF_MONTH));
		pentecote.add(GregorianCalendar.DAY_OF_MONTH, 49);
		datesFeries.add(pentecote.getTime());

		// Fête Nationale
		GregorianCalendar quatorzeJuillet = new GregorianCalendar(annee, 6, 14);
		datesFeries.add(quatorzeJuillet.getTime());

		// Assomption
		GregorianCalendar assomption = new GregorianCalendar(annee, 7, 15);
		datesFeries.add(assomption.getTime());

		// La Toussaint
		GregorianCalendar toussaint = new GregorianCalendar(annee, 10, 1);
		datesFeries.add(toussaint.getTime());

		// L'Armistice
		GregorianCalendar armistice = new GregorianCalendar(annee, 10, 11);
		datesFeries.add(armistice.getTime());

		// Noël
		GregorianCalendar noel = new GregorianCalendar(annee, 11, 25);
		datesFeries.add(noel.getTime());

		return datesFeries;
	}

	
	
	/**
	 * Retourne le jour du lundi de paque de l'année fournit en paramètre
	 * 
	 * @param annee
	 * @return la date du jour de paque
	 */
	static public GregorianCalendar calculLundiPacques(int annee) {
		int a = annee / 100;
		int b = annee % 100;
		int c = (3 * (a + 25)) / 4;
		int d = (3 * (a + 25)) % 4;
		int e = (8 * (a + 11)) / 25;
		int f = (5 * a + b) % 19;
		int g = (19 * f + c - e) % 30;
		int h = (f + 11 * g) / 319;
		int j = (60 * (5 - d) + b) / 4;
		int k = (60 * (5 - d) + b) % 4;
		int m = (2 * j - k - g + h) % 7;
		int n = (g - h + m + 114) / 31;
		int p = (g - h + m + 114) % 31;
		int jour = p + 1;
		int mois = n;

		GregorianCalendar date = new GregorianCalendar(annee, mois - 1, jour);
		date.add(GregorianCalendar.DAY_OF_MONTH, 1);
		return date;
	}
}
