package planner.model.goal;

import java.util.List;

import planner.model.timeframe.TimeFrame;

public class ParentGoal extends Goal{
	
	private List<Goal> childGoals;


	public ParentGoal(TimeFrame timeFrame, GoalDescription description) {
		super(timeFrame, description);
	}


	public List<Goal> getChildGoals() {
		return childGoals;
	}

	public void setChildGoals(List<Goal> childGoals) {
		this.childGoals = childGoals;
	}
	
	public void addChildGoal(Goal goal){
		childGoals.add(goal);
	}
}
