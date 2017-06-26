package planner.service;

import java.util.List;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface ParentGoalService {

	ParentGoal findById(Long id);
	List<Goal> findChilds(Long id);
	ParentGoal findParent(Long id);
	List<ParentGoal> findAll();
	List<ParentGoal> findYearlyGoals(String year);
	List<Goal> findMonthlyGoals(String year, String month);
	List<Goal> findWeeklyGoals(String year, String weekNumber);
	List<Goal> findDailyGoals(String year, String weeekNumber, String dayNumber);
	List<Goal> findDailyGoalsOfTheWeek(String year, String weekNumber);
	
	void saveGoal(ParentGoal goal);
	void updateGoal(ParentGoal goal);
	void deleteGoal(ParentGoal goal);
}
