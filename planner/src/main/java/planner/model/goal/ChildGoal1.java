package planner.model.goal;

import planner.model.timeframe.TimeFrame;

public class ChildGoal1 extends Goal1{
	
	private Goal1 parentGoal;

	public ChildGoal1(Goal1 goal, TimeFrame timeFrame, GoalDescription description) {
		super(timeFrame, description);
		
		this.parentGoal = goal;
	}
	
	public Goal1 getParentGoal() {
		return parentGoal;
	}

	public void setBelongsTo(Goal1 parentGoal) {
		this.parentGoal = parentGoal;
	}
}
