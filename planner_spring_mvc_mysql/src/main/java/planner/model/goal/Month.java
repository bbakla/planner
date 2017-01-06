package planner.model.goal;

import java.util.Calendar;
import java.util.Locale;

public class Month {

	private String monthName;
	private int monthNumber;
	private static Month currentMonth;
	

	public static Month[] buildMonths() {
		Calendar c = Calendar.getInstance();

		Month[] months = new Month[12];
		for (int i = 0; i <= Calendar.DECEMBER; i++) {
			String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
			months[i] = new Month(month, c.get(Calendar.MONTH));
			c.add(Calendar.MONTH, +1);
			
		}
		
		Calendar calendar = Calendar.getInstance();
		int currentMonthNumber =  calendar.get(Calendar.MONTH);
		String currentMonthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		
		currentMonth = new Month(currentMonthName, currentMonthNumber);
		
		return months;
	}

	public Month(String monthName, int montNumber) {
		super();
		this.monthName = monthName;
		this.monthNumber = montNumber;
		
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(int montNumber) {
		this.monthNumber = montNumber;
	}

	public Month getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(Month currentMonth) {
		currentMonth = currentMonth;
	}
	
	
	
	

	// public String toString(){
	// return monthName + " " + monthNumber;
	// }

}
