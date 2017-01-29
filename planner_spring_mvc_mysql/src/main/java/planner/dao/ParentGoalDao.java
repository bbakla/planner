package planner.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import planner.model.goal.Goal;
import planner.model.goal.GoalDetails;
import planner.model.goal.ParentGoal;
import planner.model.goal.scope.GoalScopeNames;

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

	@Override
	public List<ParentGoal> findYearlyGoals(int year) {
		return this.findByTimeLabel(year);
	}

	@Override
	public List<Goal> findMonthlyGoals(int year, int month) {
		
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeLabel", year))
				.add(Restrictions.eq("d.scope", GoalScopeNames.YEARLY))
//				.createAlias("childGoals.details", "c")
//				.add(Restrictions.eq("c.timeLabel", month))
//				.add(Restrictions.eq("c.scope", GoalScopeNames.MONTHLY));
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<ParentGoal> goals = (List<ParentGoal>) criteria.list();
		
		List<Goal> childGoals = new ArrayList<Goal>();
		
		for (ParentGoal parentGoal : goals) {
			List<Goal> childs = parentGoal.getChildGoals();
			for (Goal goal : childs) {
				if(goal.getDetails().getScope() == GoalScopeNames.MONTHLY && goal.getTimeLabel() == month){
					childGoals.add(goal);
				}
				
			}
		}
		
		return childGoals;
	}

	@Override
	public List<Goal> findWeeklyGoals(int year, int weekNumber) {
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeLabel", year))
				.add(Restrictions.eq("d.scope", GoalScopeNames.YEARLY))
//				.createAlias("childGoals.details", "c")
//				.add(Restrictions.eq("c.timeLabel", month))
//				.add(Restrictions.eq("c.scope", GoalScopeNames.MONTHLY));
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<ParentGoal> goals = (List<ParentGoal>) criteria.list();
		
		List<Goal> monthlyGoals = new ArrayList<Goal>();
		List<Goal> weeklyGoals = new ArrayList<Goal>();
		
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
				System.out.println(weekly.getTimeLabel() + " " + weekly.getDetails().getScope());
				if(weekly.getDetails().getScope() == GoalScopeNames.WEEKLY && weekly.getTimeLabel() == weekNumber){
					weeklyGoals.add(weekly);
				}
				
			}
		}
		
		return weeklyGoals;
		
	}

	@Override
	public List<Goal> findDailyGoals(int year, int weekNumber, int dayNumber) {
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeLabel", year))
				.add(Restrictions.eq("d.scope", GoalScopeNames.YEARLY))
//				.createAlias("childGoals.details", "c")
//				.add(Restrictions.eq("c.timeLabel", month))
//				.add(Restrictions.eq("c.scope", GoalScopeNames.MONTHLY));
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
				if(daily.getDetails().getScope() == GoalScopeNames.DAILY && daily.getTimeLabel() == dayNumber){
					dailyGoals.add(daily);
				}
			}
			
		}
		return dailyGoals;
}

	@Override
	public List<Goal> findDailyGoalsOfTheWeek(int year, int weekNumber) {
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeLabel", year))
				.add(Restrictions.eq("d.scope", GoalScopeNames.YEARLY))
//				.createAlias("childGoals.details", "c")
//				.add(Restrictions.eq("c.timeLabel", month))
//				.add(Restrictions.eq("c.scope", GoalScopeNames.MONTHLY));
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
}
