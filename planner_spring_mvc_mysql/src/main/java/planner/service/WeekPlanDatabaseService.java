package planner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import planner.dao.GenericPlanDao;
import planner.model.timeframe.WeekPlan;

@Service
public class WeekPlanDatabaseService {

	@Autowired
	@Qualifier("WeekPlanDao")
	private GenericPlanDao<WeekPlan> weekPlanDao;
	
	public void saveWeekPlan(WeekPlan weekPlan){
		if(isWeekPlanAlreadyInDatabase(weekPlan)){
			weekPlanDao.update(weekPlan);
		} else {
			weekPlanDao.save(weekPlan);
		}
	}

	private boolean isWeekPlanAlreadyInDatabase(WeekPlan weekPlan) {
		
		return weekPlanDao.findByTimeLabel(weekPlan.getYearNumber(), weekPlan.getWeekNumber()) == null ? false : true;
	}
}
