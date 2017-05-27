package planner.dao;

import java.util.List;

public interface GenericPlanDao <T> {

	T findById(Long id);
	List<T> findAll();
	T findByTimeLabel(int year, int weekNumber);
	
	void save(T entity);
	void delete(T entity);
	void update(T entity);
	
	}

