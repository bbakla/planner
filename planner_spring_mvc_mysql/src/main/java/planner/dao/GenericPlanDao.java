package planner.dao;

import java.util.List;

public interface GenericPlanDao <T> {

	T findById(Long id);
	List<T> findAll();
	T findByTimeUnit(String year, String weekNumber);
	
	void save(T entity);
	void delete(T entity);
	void update(T entity);
	
	}

