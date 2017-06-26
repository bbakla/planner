package planner.service;

import java.util.List;

import javax.management.StringValueExp;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import planner.dao.GenericDao;
import planner.dao.GoalGenericDao;
import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;


@Service("parentGoalService")
@Transactional
public class ParentGoalServiceImpl implements ParentGoalService{

	@Autowired
	@Qualifier("ParentGoalDao")
	private GoalGenericDao<ParentGoal> dao; 
	
	@Override
	public ParentGoal findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Goal> findChilds(Long id) {
		ParentGoal goal = dao.findById(id);
		
		return goal.getChildGoals();
	}

	@Override
	public ParentGoal findParent(Long id) {
		return dao.findById(id).getParentGoal();
	}

	@Override
	public List<ParentGoal> findAll() {
		return dao.findAll();
	}
	
	@Override
	public List<ParentGoal> findYearlyGoals(String timeUnit) {
		return dao.findByTimeUnit(timeUnit);
	}
	
	@Override
	public List<Goal> findMonthlyGoals(String year, String month) {
		return dao.findMonthlyGoals(year, month);
	}

	@Override
	public List<Goal> findWeeklyGoals(String year, String weekNumber) {
		return dao.findWeeklyGoals(year, weekNumber);
	}

	@Override
	public List<Goal> findDailyGoals(String year, String weeekNumber, String dayNumber) {
		return dao.findDailyGoals(year, weeekNumber, dayNumber);
	}
	
	@Override
	public List<Goal> findDailyGoalsOfTheWeek(String year, String weekNumber) {
		return dao.findByTimeUnit(year, weekNumber);
	}

	@Override
	public void saveGoal(ParentGoal goal) {
		dao.save(goal);
	}

	@Override
	public void updateGoal(ParentGoal goal) {
		dao.update(goal);
	}

	@Override
	public void deleteGoal(ParentGoal goal) {
		dao.delete(goal);
	}

	
}
