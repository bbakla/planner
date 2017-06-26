package planner.service;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import planner.model.enums.Day;
import planner.model.enums.WeekPlannerTimeSlot;
import planner.model.goal.GoalIdentity;
import planner.model.goal.ParentGoal;
import planner.model.timeframe.DayPlan;
import planner.model.timeframe.WeekPlan;

@Service
@Transactional
public class WeekPlanConverterService {

	@Autowired
	private ParentGoalService dao;
	
	private JsonParser parser;
	private JsonObject weekAsJsonObject;
	
	

	public WeekPlan convertjsonToWeekPlan(String weekPlan, String year, String weekNumber) {

		initService(weekPlan);
		Map<Day, Map<String, String>> weekPlanAsMap = new HashMap<>();

		List<String> keysOfWeek = getKeysOfWeek();

		for (String key : keysOfWeek) {
			String day = weekAsJsonObject.getAsJsonObject(key.toString()).toString();
			weekPlanAsMap.put(Day.findByKey(key), getDayToMap(day));
		}

		return convertMapToWeekPlan(weekPlanAsMap, year, weekNumber);
	}

	private WeekPlan convertMapToWeekPlan(Map<Day, Map<String, String>> weekPlanAsMap, String year, String weekNumber) {

		WeekPlan weekPlan = new WeekPlan(weekNumber, year);

		for (Map.Entry<Day, Map<String, String>> entry : weekPlanAsMap.entrySet()) {

			DayPlan dayPlan = new DayPlan(entry.getKey(), getGoals(entry.getValue()));
			weekPlan.addDailyPlan(dayPlan);
		}
		
		

		return weekPlan;
	}

	private Map<WeekPlannerTimeSlot, GoalIdentity> getGoals(Map<String, String> dayGoals) {
		Map<WeekPlannerTimeSlot, GoalIdentity> goals = new HashMap<>();
		for (Map.Entry<String, String> entry : dayGoals.entrySet()) {
			Long goalId = entry.getValue() == null || entry.getValue().length() == 0 ? null
					: Long.parseLong(entry.getValue());
			
			GoalIdentity goalIdentity = null;
			
			if(goalId != null){
				ParentGoal goal = dao.findById(goalId);
				goalIdentity = new GoalIdentity(goal.getTitle(), goalId);
			} 
			goals.put(WeekPlannerTimeSlot.findByKey(entry.getKey()), goalIdentity);
		}

		return goals;
	}

	private void initService(String weekPlan) {
		this.parser = new JsonParser();

		weekAsJsonObject = parser.parse(weekPlan).getAsJsonObject();
		weekAsJsonObject = weekAsJsonObject.getAsJsonObject("dto");
	}

	private List<String> getKeysOfWeek() {
		Set<Map.Entry<String, JsonElement>> entries = weekAsJsonObject.entrySet();

		return weekAsJsonObject.entrySet().stream().map(i -> i.getKey())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private Map<String, String> getDayToMap(String day) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();

		return new Gson().fromJson(day, type);
	}
}
