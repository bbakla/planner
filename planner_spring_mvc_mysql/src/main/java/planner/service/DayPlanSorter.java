package planner.service;

import java.util.Comparator;

import planner.model.timeframe.DayPlan;

public class DayPlanSorter implements Comparator<DayPlan>{

	@Override
	public int compare(DayPlan day1, DayPlan day2) {
		return Integer.valueOf(day1.getDay().getDayNumber()).compareTo(day2.getDay().getDayNumber());
	}

}
