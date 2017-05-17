package planner.dao;

import java.util.List;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface GenericDao <T> {

	T findById(Long id);
	List<T> findAll();
	List<T> findByTimeLabel(int time);
	List<Goal> findDailyGoalsOfTheWeek(int year, int weekNumber);
	
	void save(T entity);
	void delete(T entity);
	void update(T entity);
	
	}

