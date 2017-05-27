package planner.service;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import planner.model.timeframe.DayPlan;
import planner.model.timeframe.WeekPlan;
import planner.model.timeframe.WeekPlannerTimeSlot;

@Service
@Transactional
public class WeekPlanConverterService {

	private JsonParser parser;
	private JsonObject weekAsJsonObject;

	public WeekPlan convertjsonToWeekPlan(String weekPlan, int year, int weekNumber) {

		initService(weekPlan);
		Map<String, Map<String, String>> weekPlanAsMap = new HashMap<>();

		List<String> keysOfWeek = getKeysOfWeek();

		for (String key : keysOfWeek) {
			String day = weekAsJsonObject.getAsJsonObject(key).toString();
			weekPlanAsMap.put(key, getDayToMap(day));
		}

		return convertMapToWeekPlan(weekPlanAsMap, year, weekNumber);
	}

	private WeekPlan convertMapToWeekPlan(Map<String, Map<String, String>> weekPlanAsMap, int year, int weekNumber) {

		WeekPlan weekPlan = new WeekPlan(weekNumber, year);

		for (Map.Entry<String, Map<String, String>> entry : weekPlanAsMap.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

			DayPlan dayPlan = new DayPlan(entry.getKey(), getGoals(entry.getValue()));
			weekPlan.addDailyPlan(dayPlan);
		}

		return weekPlan;
	}

	private Map<WeekPlannerTimeSlot, Long> getGoals(Map<String, String> dayGoals) {
		Map<WeekPlannerTimeSlot, Long> goals = new HashMap<>();
		for (Map.Entry<String, String> entry : dayGoals.entrySet()) {
			Long goalId = entry.getValue() == null || entry.getValue().length() == 0 ? null
					: Long.parseLong(entry.getValue());
			goals.put(WeekPlannerTimeSlot.findByKey(entry.getKey()), goalId);
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
