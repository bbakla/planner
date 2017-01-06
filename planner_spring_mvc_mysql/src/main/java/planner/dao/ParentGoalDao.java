package planner.dao;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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
	public void save(ParentGoal goal) {
		
		ParentGoal parentGoal = goal.getParentGoal();
		if(parentGoal != null && parentGoal.getId() != null){
			this.updateEntity(goal);
		} else {
			super.persistEntity(goal); 
		}
		
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
						.add(Restrictions.eq("d.timeLabel", time))
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			
//			ProjectionList projectionList = Projections.projectionList();
//			projectionList.add(Projections.property("d.timeLabel"), "d.timeLabel");
//			criteria.setProjection(projectionList);
			
			List<ParentGoal> goals = criteria.list();
			
			
			
			return goals;
		}
}
