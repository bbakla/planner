package planner.service;

import java.util.List;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface ParentGoalService {

	ParentGoal findById(Long id);
	List<Goal> findChilds(Long id);
	ParentGoal findParent(Long id);
	List<ParentGoal> findAll();
	List<ParentGoal> findYearlyGoals(int year);
	List<Goal> findMonthlyGoals(int year, int month);
	List<Goal> findWeeklyGoals(int year, int weekNumber);
	List<Goal> findDailyGoals(int year, int weeekNumber, int dayNumber);
	List<Goal> findDailyGoalsOfTheWeek(int year, int weekNumber);
	
	void saveGoal(ParentGoal goal);
	void updateGoal(ParentGoal goal);
	void deleteGoal(ParentGoal goal);
}
