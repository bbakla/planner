package planner.dao;

import java.util.List;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface GoalGenericDao<T> extends GenericDao<T> {
	List<ParentGoal> findYearlyGoals(int year);
	List<Goal> findMonthlyGoals(int year, int month);
	List<Goal> findWeeklyGoals(int year, int weekNumber);
	List<Goal> findDailyGoals(int year, int weeekNumber, int dayNumber);
}
