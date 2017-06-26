package planner.dao;

import java.util.List;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface GoalGenericDao<T> extends GenericDao<T> {
	List<ParentGoal> findYearlyGoals(String year);
	List<Goal> findMonthlyGoals(String year, String month);
	List<Goal> findWeeklyGoals(String year, String weekNumber);
	List<Goal> findDailyGoals(String year, String weeekNumber, String dayNumber);
}
