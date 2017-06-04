package planner.model.enums;

public enum Day {

	MONDAY(1, "Monday"),
	TUESDAY(2, "Tuesday"),
	WEDNESDAY(3, "Wednesday"),
	THURSDAY(4, "Thursday"),
	FRIDAY(5, "Friday"),
	SATURDAY(6, "Saturday"),
	SUNDAY(7,"Sunday");
	
	
	private int dayNumber;
	private String dayName;
	
	Day(int number, String dayName){
		this.dayNumber = number;
		this.dayName = dayName;
	}

	public int getDayNumber() {
		return dayNumber;
	}
	
	public static Day findByKey(String abbr){
	    for(Day v : values()){
	        if( v.dayName.equalsIgnoreCase(abbr)){
	            return v;
	        }
	    }
	    return null;
	}
}
