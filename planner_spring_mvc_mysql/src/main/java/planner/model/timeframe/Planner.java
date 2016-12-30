package planner.model.timeframe;

import java.util.List;

import planner.model.goal.Goal;

public interface Planner {
	
	public void addGoal(Goal goal);
	public List<Goal> getGoals(); 

}
