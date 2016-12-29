package planner.model.timeframe;

import java.util.List;

import planner.model.goal.Goal;

public interface GoalScope {
	
	public void addGoal(Goal goal);
	public List<Goal> getGoals(); 

}
