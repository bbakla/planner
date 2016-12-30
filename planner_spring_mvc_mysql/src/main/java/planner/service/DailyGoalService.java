package planner.service;

import java.util.List;

import planner.model.goal.DailyGoal;
import planner.model.goal.Goal;
import planner.model.goal.ParentGoal;

public interface DailyGoalService {

	DailyGoal findById(Long id);
	ParentGoal findParent(Long id);
	List<DailyGoal> findAll();
	
	void saveGoal(DailyGoal goal);
	void updateGoal(DailyGoal goal);
	void deleteGoal(DailyGoal goal);
}
