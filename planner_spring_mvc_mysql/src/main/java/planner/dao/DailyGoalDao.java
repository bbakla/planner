package planner.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import planner.model.goal.DailyGoal;
import planner.model.goal.ParentGoal;

@Repository("ChildGoalDao")
@Transactional
public class DailyGoalDao extends AbstractDao<Long, DailyGoal>  implements GenericDao<DailyGoal> {

	@Override
	public DailyGoal findById(Long id) {
		return getByKey(id);
	}

	@Override
	public List<DailyGoal> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<DailyGoal>) criteria.list();
	}

	@Override
	public void save(DailyGoal entity) {
		if(entity.getParentGoal().getId() == null){
			super.persistEntity(entity);
		} else {
			super.mergeEntity(entity);
		}
		
	}

	@Override
	public void delete(DailyGoal entity) {
		super.deleteEntity(entity);
	}

	@Override
	public void update(DailyGoal entity) {
		super.updateEntity(entity);
	}
}
