package planner.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import planner.model.goal.Goal;
import planner.model.goal.GoalDetails;
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

	@Override
	public List<ParentGoal> findByTimeLabel(int time) {
			Criteria criteria = createEntityCriteria()
						.createAlias("details", "d")
						.add(Restrictions.eq("d.timeLabel", time));
			
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("d.timeLabel"), "d.timeLabel");
			criteria.setProjection(projectionList);
			
			return (List<ParentGoal>) criteria.list();
		}
}
