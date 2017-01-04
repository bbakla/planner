package planner.service;

import java.util.List;

import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface ParentGoalService {

	ParentGoal findById(Long id);
	List<Goal> findChilds(Long id);
	ParentGoal findParent(Long id);
	List<ParentGoal> findAll();
	List<ParentGoal> findYearlyGoals(int timeLabel);
	List<Goal> findMonthlyGoals(int timeLabel);
//	List<Goal> findWeeklyGoals(int timeLA)
	
	void saveGoal(ParentGoal goal);
	void updateGoal(ParentGoal goal);
	void deleteGoal(ParentGoal goal);
	
	
}
