package planner.model.goal;

import planner.model.timeframe.TimeFrame;

public abstract class Goal {
	
	protected GoalDetails details;
	
	public Goal(TimeFrame timeFrame, GoalDescription description) {
		details = new GoalDetails(timeFrame, description, GoalStatus.NOT_STARTED);
	}
	
	public void setStatus(GoalStatus status) {
		details.setStatus(status);
		
	}

	public TimeFrame getTimeFrame() {
		return details.getTimeFrame();
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		details.setTimeFrame(timeFrame);
	}

	public GoalDescription getDescription() {
		return details.getDescription();
	}

	public void setDescription(GoalDescription description) {
		details.setDescription(description);
	}
}
