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
public class WeekPlanDao extends AbstractDao<Long, WeekPlan>  implements GenericDao<WeekPlan> {

	@Override
	public WeekPlan findById(Long id) {
		return getByKey(id);
	}

	@Override
	public List<WeekPlan> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<WeekPlan>) criteria.list();
	}

	/**
	 * TODO: This implementation should create a criteria with week and year.
	 * Therefore, current implementation is wrong.
	 * 
	 */
	@Override
	public List<WeekPlan> findByTimeLabel(int time) {
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeLabel", time))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	
	
	List<WeekPlan> goals = criteria.list();
	
	return goals;
	}


	/**
	 * TODO: Not correct
	 */

	@Override
	public List<Goal> findDailyGoalsOfTheWeek(int year, int weekNumber) {
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeLabel", year))
				.add(Restrictions.eq("d.scope", GoalScopeNames.YEARLY))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<ParentGoal> goals = (List<ParentGoal>) criteria.list();
		
		List<Goal> monthlyGoals = new ArrayList<Goal>();
		List<Goal> weeklyGoals = new ArrayList<Goal>();
		List<Goal> dailyGoals = new ArrayList<Goal>();
		
		for (ParentGoal parentGoal : goals) {
			List<Goal> childs = parentGoal.getChildGoals();
			for (Goal goal : childs) {
				if(goal.getDetails().getScope() == GoalScopeNames.MONTHLY){
					monthlyGoals.add(goal);
				}
			}
		}
		
		for (Goal monthlyGoal : monthlyGoals) {
			ParentGoal goal = (ParentGoal) monthlyGoal;
			List<Goal> childs = goal.getChildGoals();
			for (Goal weekly : childs) {
				if(weekly.getDetails().getScope() == GoalScopeNames.WEEKLY && weekly.getTimeLabel() == weekNumber){
					weeklyGoals.add(weekly);
			}
		}
		}	
		
		for(Goal weeklyGoal : weeklyGoals){
			ParentGoal weekly = (ParentGoal) weeklyGoal;
			List<Goal> childs = weekly.getChildGoals();
			
			for(Goal daily : childs){
				if(daily.getDetails().getScope() == GoalScopeNames.DAILY){
					dailyGoals.add(daily);
				}
			}
			
		}
		return dailyGoals;
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
