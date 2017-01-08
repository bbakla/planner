package planner.dao;

import java.util.List;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface GenericDao <T> {

	T findById(Long id);
	List<T> findAll();
	List<T> findByTimeLabel(int time);
	List<ParentGoal> findYearlyGoals(int year);
	List<Goal> findMonthlyGoals(int year, int month);
	List<Goal> findWeeklyGoals(int year, int weekNumber);
	List<Goal> findDailyGoals(int year, int weeekNumber, int dayNumber);
	
	void save(T entity);
	void delete(T entity);
	void update(T entity);
	}

