package planner.dao;

import java.util.List;

public interface GenericDao <T> {

	T findById(Long id);
	List<T> findAll();
	List<T> findByTimeLabel(int time);
	void save(T entity);
	void delete(T entity);
	void update(T entity);
	}

