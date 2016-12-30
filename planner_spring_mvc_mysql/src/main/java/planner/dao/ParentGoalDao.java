package planner.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import planner.model.goal.ParentGoal;

@Repository("ParentGoalDao")
@Transactional
public class ParentGoalDao extends AbstractDao<Long, ParentGoal>  implements GenericDao<ParentGoal> {

	@Override
	public ParentGoal findById(Long id) {
		return getByKey(id);
	}

	@Override
	public List<ParentGoal> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<ParentGoal>) criteria.list();
	}

	@Override
	public void save(ParentGoal entity) {
		super.persistEntity(entity);
	}

	@Override
	public void delete(ParentGoal entity) {
		super.deleteEntity(entity);
	}

	@Override
	public void update(ParentGoal entity) {
		super.updateEntity(entity);
	}
}
