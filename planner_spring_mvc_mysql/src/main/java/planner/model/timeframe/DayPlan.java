package planner.model.timeframe;

import java.util.HashMap;
import java.util.Map;

public class DayPlan {
	
	private Map<String, String> goals = new HashMap<>();
	
	
	
	public DayPlan()
		{
			goals.put("till_9", "");
			goals.put("till_10", "");
			goals.put("till_11", "");
			goals.put("till_12", "");
			goals.put("till_13", "");
			goals.put("till_14", "");
			goals.put("till_15", "");
			goals.put("till_16", "");
			goals.put("till_17", "");
			goals.put("after_17", "");
	}
	
	

}
