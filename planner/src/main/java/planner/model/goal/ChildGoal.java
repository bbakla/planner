package planner.model.goal;

import planner.model.timeframe.TimeFrame;

public class ChildGoal extends Goal{
	
	private Goal parentGoal;

	public ChildGoal(Goal parentGoal, TimeFrame timeFrame, GoalDescription description) {
		super(timeFrame, description);
		
		this.parentGoal = parentGoal;
	}
	
	public Goal getParentGoal() {
		return parentGoal;
	}

	public void setBelongsTo(Goal parentGoal) {
		this.parentGoal = parentGoal;
	}
}
