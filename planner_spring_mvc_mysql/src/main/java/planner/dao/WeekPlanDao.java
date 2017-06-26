package planner.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import planner.model.goal.Goal;
import planner.model.goal.GoalScopeNames;
import planner.model.goal.ParentGoal;
import planner.model.timeframe.WeekPlan;

@Repository("WeekPlanDao")
@Transactional
public class WeekPlanDao extends AbstractDao<Long, WeekPlan>  implements GenericPlanDao<WeekPlan> {

	@Override
	public WeekPlan findById(Long id) {
		return getByKey(id);
	}

	@Override
	public List<WeekPlan> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<WeekPlan>) criteria.list();
	}

	@Override
	public WeekPlan findByTimeUnit(String year, String weekNumber) {
		Criteria criteria = createEntityCriteria()
				.add(Restrictions.eq("yearNumber", year))
				.add(Restrictions.eq("weekNumber", weekNumber))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return (WeekPlan) criteria.uniqueResult();
	}

	@Override
	public void save(WeekPlan weekPlan) {

			super.persistEntity(weekPlan); 
	}

	@Override
	public void delete(WeekPlan entity) {
		super.deleteEntity(entity);
	}

	@Override
	public void update(WeekPlan entity) {

		super.updateEntity(entity);
	}

	

	
}
