package planner.model.enums;

public enum WeekPlannerTimeSlot {

	TILL_9("till_9"),
	TILL_10("till_10"),
	TILL_11("till_11"),
	TILL_12("till_12"),
	TILL_13("till_13"),
	TILL_14("till_14"),
	TILL_15("till_15"),
	TILL_16("till_16"),
	TILL_17("till_17"),
	AFTER_17("after_17");
	
	
	private String timeSlot;
	
	WeekPlannerTimeSlot(String timeSlot){
		this.timeSlot = timeSlot;
	}
	
	public String getTimeSlot(){
		return timeSlot;
	}
	
	public static WeekPlannerTimeSlot findByKey(String abbr){
	    for(WeekPlannerTimeSlot v : values()){
	        if( v.timeSlot.equals(abbr)){
	            return v;
	        }
	    }
	    return null;
	}
}
