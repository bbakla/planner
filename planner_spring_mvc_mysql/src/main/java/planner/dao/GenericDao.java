package planner.dao;

import java.util.List;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface GenericDao <T> {

	T findById(Long id);
	List<T> findAll();
	List<T> findByTimeUnit(String time);
	List<Goal> findByTimeUnit(String year, String weekNumber);
	
	void save(T entity);
	void delete(T entity);
	void update(T entity);
	
	}

