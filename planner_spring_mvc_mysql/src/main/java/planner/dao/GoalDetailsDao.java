package planner.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import planner.model.goal.GoalDescription;
import planner.model.goal.GoalDetails;

@Repository("GoalDetailsDao")
@Transactional
public class GoalDetailsDao extends AbstractDao<Long, GoalDetails>  implements GenericDao<GoalDetails> {

	@Override
	public GoalDetails findByID(Long id) {
		return getByKey(id);
	}

	@Override
	public List<GoalDetails> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<GoalDetails>) criteria.list();
	}

	@Override
	public void save(GoalDetails entity) {
		super.persistEntity(entity);
	}

	@Override
	public void delete(GoalDetails entity) {
		super.deleteEntity(entity);
	}

	@Override
	public void update(GoalDetails entity) {
		super.updateEntity(entity);
	}

}
