package planner.model.enums;

public enum Day {

	MONDAY(1),
	TUESDAY(2),
	WEDNESDAY(3),
	THURSDAY(4),
	FRIDAY(5),
	SATURDAY(6),
	SUNDAY(7);
	
	
	private int dayNumber;
	
	Day(int number){
		this.dayNumber = number;
	}

	public int getDayNumber() {
		return dayNumber;
	}
	
}
