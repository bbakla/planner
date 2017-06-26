package planner.service;


import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import planner.dao.GenericPlanDao;
import planner.model.enums.WeekPlannerTimeSlot;
import planner.model.timeframe.DayPlan;
import planner.model.timeframe.WeekPlan;

@Service
public class WeekPlanDatabaseService {

	private static final Logger logger = Logger.getLogger(WeekPlanDatabaseService.class);

	@Autowired
	@Qualifier("WeekPlanDao")
	private GenericPlanDao<WeekPlan> weekPlanDao;
	
	@Autowired
	private PlanSorter sorter;

	public void saveWeekPlan(WeekPlan weekPlan) {
		try {
			WeekPlan weekPlanInDatabase = weekPlanDao.findByTimeUnit(weekPlan.getYearNumber(),
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

				System.out.println(" fdgdfg " + weekPlan.getWeekPlan().get(0).getGoals().get(WeekPlannerTimeSlot.TILL_11));
				weekPlanDao.update(weekPlan);
				
			} else {
				System.out.println(" fdgdfg " + weekPlan.getWeekPlan().get(0).getGoals().get(WeekPlannerTimeSlot.TILL_11));
				weekPlanDao.save(weekPlan);
			}
		} catch (ConstraintViolationException e) {
			logger.error("This weekPlan is already saved.");
		}

	}

	public WeekPlan getSortedWeekPlan(String yearNumber, String weekNumber) {
		WeekPlan weekPlan =  weekPlanDao.findByTimeUnit(yearNumber, weekNumber);
		
		if(weekPlan == null){
			weekPlan = new WeekPlan(weekNumber, yearNumber);
		}
		sorter.completeMissingDays(weekPlan);
		
		return weekPlan;
	}
	
	public WeekPlan getSortedWeekPlan(WeekPlan weekPlan) {
		
		sorter.completeMissingDays(weekPlan);
		
		return weekPlan;
	}

	
}
