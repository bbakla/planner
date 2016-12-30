package planner.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import planner.model.goal.GoalDescription;

@Repository("GoalDescriptionDao")
@Transactional
public class GoalDescriptionDao extends AbstractDao<Long, GoalDescription>  implements GenericDao<GoalDescription> {

	@Override
	public GoalDescription findById(Long id) {
		return getByKey(id);
	}

	@Override
	public List<GoalDescription> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<GoalDescription>) criteria.list();
	}

	@Override
	public void save(GoalDescription entity) {
		super.persistEntity(entity);
	}

	@Override
	public void delete(GoalDescription entity) {
		super.deleteEntity(entity);
	}

	@Override
	public void update(GoalDescription entity) {
		super.updateEntity(entity);
	}

}
