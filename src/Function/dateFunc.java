package Function;

import java.util.Calendar;

public class dateFunc {
	private static Calendar cal = Calendar.getInstance();
	
	public static int getDay() {
		return cal.get(Calendar.DATE);
	}
	
	public static int getMonth() {
		return cal.get(Calendar.MONTH) + 1;
	}
	
	public static int getYear() {
		return cal.get(Calendar.YEAR);
	}
	
	public static int getHour() {
		return cal.get(Calendar.HOUR);
	}
	
	public static int getMin() {
		return cal.get(Calendar.MINUTE);
	}
	
	public static String getToday() {
		return String.valueOf(dateFunc.getYear()) + "-" + String.valueOf(dateFunc.getMonth()) + "-" + String.valueOf(dateFunc.getDay())
		+ " " + String.valueOf(dateFunc.getHour()) + "½Ã " + String.valueOf(dateFunc.getMin() + "ºÐ");
	}
}
