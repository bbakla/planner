package planner.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;
import planner.model.goal.GoalScopeNames;

@Repository("ParentGoalDao")
@Transactional
public class ParentGoalDao extends AbstractDao<Long, ParentGoal>  implements GoalGenericDao<ParentGoal> {
	
	private static final Logger logger = Logger.getLogger(ParentGoalDao.class);

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
			logger.info(goal.getTitle() + " is being updated ");
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
	public List<ParentGoal> findByTimeUnit(String time) {
			Criteria criteria = createEntityCriteria()
						.createAlias("details", "d")
						.add(Restrictions.eq("d.timeUnit", time))
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			List<ParentGoal> goals = criteria.list();
			
			return goals;
		}

	@Override
	public List<ParentGoal> findYearlyGoals(String year) {
		return this.findByTimeUnit(year);
	}

	@Override
	public List<Goal> findMonthlyGoals(String year, String month) {
		
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeUnit", year))
				.add(Restrictions.eq("d.scope", GoalScopeNames.YEARLY))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<ParentGoal> goals = (List<ParentGoal>) criteria.list();
		
		List<Goal> childGoals = new ArrayList<Goal>();
		
		for (ParentGoal parentGoal : goals) {
			List<Goal> childs = parentGoal.getChildGoals();
			for (Goal goal : childs) {
				if(goal.getDetails().getScope() == GoalScopeNames.MONTHLY && goal.getTimeUnit().equals(month)){
					childGoals.add(goal);
				}
				
			}
		}
		
		return childGoals;
	}

	@Override
	public List<Goal> findWeeklyGoals(String year, String weekNumber) {
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeUnit", year))
				.add(Restrictions.eq("d.scope", GoalScopeNames.YEARLY))
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
				System.out.println(weekly.getTimeUnit() + " " + weekly.getDetails().getScope());
				
				if(weekly.getDetails().getScope() == GoalScopeNames.WEEKLY && weekly.getTimeUnit().equals(Integer.valueOf(weekNumber).toString())){
					weeklyGoals.add(weekly);
				}
				
			}
		}
		
		return weeklyGoals;
		
	}

	@Override
	public List<Goal> findDailyGoals(String year, String weekNumber, String dayNumber) {
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeUnit", year))
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
				if(weekly.getDetails().getScope() == GoalScopeNames.WEEKLY && weekly.getTimeUnit().equals(Integer.valueOf(weekNumber).toString())){
					weeklyGoals.add(weekly);
			}
		}
		}	
		
		for(Goal weeklyGoal : weeklyGoals){
			ParentGoal weekly = (ParentGoal) weeklyGoal;
			List<Goal> childs = weekly.getChildGoals();
			
			for(Goal daily : childs){
				if(daily.getDetails().getScope() == GoalScopeNames.DAILY && daily.getTimeUnit().equals(dayNumber)){
					dailyGoals.add(daily);
				}
			}
			
		}
		return dailyGoals;
}

	@Override
	public List<Goal> findByTimeUnit(String year, String weekNumber) {
		Criteria criteria = createEntityCriteria()
				.createAlias("details", "d")
				.add(Restrictions.eq("d.timeUnit", year))
				.add(Restrictions.eq("d.scope", GoalScopeNames.YEARLY))
//				.createAlias("childGotimeUnitc.scope", GoalScopeNames.MONTHLY));
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
				if(weekly.getDetails().getScope() == GoalScopeNames.WEEKLY && weekly.getTimeUnit().equals(Integer.valueOf(weekNumber).toString())){
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
