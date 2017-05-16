package planner.model.timeframe;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="weekPlan")
public class WeekPlan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="plan_id")
	protected Long id;
	

	@Column
	private int yearNumber;
	
	@Column
	private int weekNumber;
	
	
	private Map<String, DayPlan> weekPlan = new HashMap<>();
	
	public WeekPlan(){
		weekPlan.put("Monday", null);
		weekPlan.put("Tuesday", null);
		weekPlan.put("Wednesday", null);
		weekPlan.put("Thursday", null);
		weekPlan.put("Friday", null);
		weekPlan.put("Saturday", null);
		weekPlan.put("Sunday", null);
	}

	public Map<String, DayPlan> getWeekPlan() {
		return weekPlan;
	}
	
	

//	private Map<String, String> goals = new HashMap<>();
	/*
	public WeekPlan(){
		goals.put("monday_9", "");
		goals.put("monday_10", "");
		goals.put("monday_11", "");
		goals.put("monday_12", "");
		goals.put("monday_13", "");
		goals.put("monday_14", "");
		goals.put("monday_15", "");
		goals.put("monday_16", "");
		goals.put("monday_17", "");
		goals.put("monday_after_17", "");
		
		goals.put("tuesday_9", "");
		goals.put("tuesday_10", "");
		goals.put("tuesday_11", "");
		goals.put("tuesday_12", "");
		goals.put("tuesday_13", "");
		goals.put("tuesday_14", "");
		goals.put("tuesday_15", "");
		goals.put("tuesday_16", "");
		goals.put("tuesday_17", "");
		goals.put("tuesday_17_after", "");
		
		goals.put("wednesday_9", "");
		goals.put("wednesday_10", "");
		goals.put("wednesday_11", "");
		goals.put("wednessday_12", "");
		goals.put("wednesday_13", "");
		goals.put("wednesday_14", "");
		goals.put("wednesday_15", "");
		goals.put("wednesday_16", "");
		goals.put("wednesday_17", "");
		goals.put("wednesday_17_after", "");
		
		goals.put("thursday_9", "");
		goals.put("thursday_10", "");
		goals.put("thursday_11", "");
		goals.put("thursday_12", "");
		goals.put("thursday_13", "");
		goals.put("thursday_14", "");
		goals.put("thursday_15", "");
		goals.put("thursday_16", "");
		goals.put("thursday_17", "");
		goals.put("thursday_17_after", "");
		
		goals.put("friday_9", "");
		goals.put("friday_10", "");
		goals.put("friday_11", "");
		goals.put("friday_12", "");
		goals.put("friday_13", "");
		goals.put("friday_14", "");
		goals.put("friday_15", "");
		goals.put("friday_16", "");
		goals.put("friday_17", "");
		goals.put("friday_17_after", "");
		
		goals.put("saturday_9", "");
		goals.put("saturday_10", "");
		goals.put("saturday_11", "");
		goals.put("saturday_12", "");
		goals.put("saturday_13", "");
		goals.put("saturday_14", "");
		goals.put("saturday_15", "");
		goals.put("saturday_16", "");
		goals.put("saturday_17", "");
		goals.put("saturday_17_after", "");
		
		goals.put("sunday_9", "");
		goals.put("sunday_10", "");
		goals.put("sunday_11", "");
		goals.put("sunday_12", "");
		goals.put("sunday_13", "");
		goals.put("sunday_14", "");
		goals.put("sunday_15", "");
		goals.put("sunday_16", "");
		goals.put("sunday_17", "");
		goals.put("sunday_17_after", "");
	}
	*/
}
