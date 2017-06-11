package planner.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import planner.model.enums.Day;
import planner.model.enums.WeekPlannerTimeSlot;
import planner.model.goal.GoalIdentity;
import planner.model.timeframe.DayPlan;
import planner.model.timeframe.WeekPlan;

@Service
public class PlanSorter {
	
	public void completeMissingDays(WeekPlan weekPlan) {
		List<DayPlan> dayPlansOfWeek = weekPlan.getWeekPlan();
		
		for(DayPlan dayPlan : dayPlansOfWeek){

			dayPlan.setGoals(completeEmptyTimeSlotsOfADayPlan(dayPlan.getGoals()));
			
		}
		
		completeEmptyDays(dayPlansOfWeek);
		
	}
	
	private void completeEmptyDays(List<DayPlan> dailyPlansOfWeek) {

		Collections.sort(dailyPlansOfWeek);

		int numberOfDaysInAWeek = 7;
		Day[] days = Day.values();

		for (int i = dailyPlansOfWeek.size(); i < numberOfDaysInAWeek; i++) {
			DayPlan dayPlan = new DayPlan(days[i]);
			dayPlan.setGoals(completeEmptyTimeSlotsOfADayPlan(dayPlan.getGoals()));

			dayPlan.getGoals().forEach((key, value) -> System.out.println(key + " " + value));
			
			if(!dailyPlansOfWeek.contains(dayPlan)){
				dailyPlansOfWeek.add(dayPlan);
			}
			
		}
		
		Collections.sort(dailyPlansOfWeek);

	}

	private Map<WeekPlannerTimeSlot, GoalIdentity> completeEmptyTimeSlotsOfADayPlan(Map<WeekPlannerTimeSlot, GoalIdentity> goalsByHourlyTimeSlot){
		Map<WeekPlannerTimeSlot, GoalIdentity> goalsByHourlyTimeSlotSorted = new TreeMap<>(goalsByHourlyTimeSlot);
		for(WeekPlannerTimeSlot timeSlot : WeekPlannerTimeSlot.values())
		{
			if(!goalsByHourlyTimeSlot.containsKey(timeSlot)){
				goalsByHourlyTimeSlotSorted.put(timeSlot, null);
			}
		}
		
		return goalsByHourlyTimeSlotSorted;
	}

}
