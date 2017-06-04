package planner.service;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import planner.controller.WeekPlannerController;
import planner.dao.GenericPlanDao;
import planner.model.enums.Day;
import planner.model.timeframe.DayPlan;
import planner.model.timeframe.WeekPlan;

@Service
public class WeekPlanDatabaseService {

	private static final Logger logger = Logger.getLogger(WeekPlanDatabaseService.class);

	@Autowired
	@Qualifier("WeekPlanDao")
	private GenericPlanDao<WeekPlan> weekPlanDao;

	public void saveWeekPlan(WeekPlan weekPlan) {
		try {
			WeekPlan weekPlanInDatabase = weekPlanDao.findByTimeLabel(weekPlan.getYearNumber(),
					weekPlan.getWeekNumber());
			if (weekPlanInDatabase != null) {
				weekPlan.setId(weekPlanInDatabase.getId());
				List<DayPlan> dailyPlansInDatabase = weekPlanInDatabase.getWeekPlan();
				List<DayPlan> newdailyPlans = weekPlan.getWeekPlan();

				Iterator<DayPlan> inDatabasePlansIterator = dailyPlansInDatabase.iterator();
				Iterator<DayPlan> newOnesIterator = newdailyPlans.iterator();

				while (inDatabasePlansIterator.hasNext()) {
					DayPlan newPlan = newOnesIterator.next();
					DayPlan inDatabase = inDatabasePlansIterator.next();

					newPlan.setId(inDatabase.getId());
				}

				weekPlanDao.update(weekPlan);
			} else {
				weekPlanDao.save(weekPlan);
			}
		} catch (ConstraintViolationException e) {
			logger.error("This weekPlan is already saved.");
		}

	}

	public WeekPlan getWeekPlan(int yearNumber, int weekNumber) {
		return weekPlanDao.findByTimeLabel(yearNumber, weekNumber);
	}

	private void completeEmptyDays(List<DayPlan> dailyPlansOfWeek) {

		Collections.sort(dailyPlansOfWeek);

		int numberOfDaysInAWeek = 7;
		Day[] days = Day.values();

		for (int i = dailyPlansOfWeek.size(); i < numberOfDaysInAWeek; i++) {
			DayPlan dayPlan = new DayPlan(days[i]);
			dailyPlansOfWeek.add(dayPlan);
		}
		
		Collections.sort(dailyPlansOfWeek);

	}

	private boolean isWeekPlanIdIfAlreadyInDatabase(WeekPlan weekPlan) {
		WeekPlan weekPlanInDatabase = weekPlanDao.findByTimeLabel(weekPlan.getYearNumber(), weekPlan.getWeekNumber());
		if (weekPlanInDatabase != null) {
			weekPlan.setId(weekPlanInDatabase.getId());
		}
		return weekPlanDao.findByTimeLabel(weekPlan.getYearNumber(), weekPlan.getWeekNumber()) == null ? false : true;
	}

	public void completeMissingDays(WeekPlan weekPlan) {
		List<DayPlan> dayPlansOfWeek = weekPlan.getWeekPlan();

		completeEmptyDays(dayPlansOfWeek);
		
		dayPlansOfWeek.forEach(j -> System.out.println(j.getDay().name() + " " + j.getGoals().size()));

	}
}
