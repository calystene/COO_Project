package util.date;

public class DateManager {
	private static DateManager singleton;
	
	private java.util.Date uDate;
	private java.sql.Date sDate;
	
	private DateManager() {
		uDate = new java.util.Date();
		sDate = new java.sql.Date(System.currentTimeMillis());
		
		
	}
	
	public DateManager getInstance() {
		if(singleton==null)
			new DateManager();
		
		return singleton;
	}
}
