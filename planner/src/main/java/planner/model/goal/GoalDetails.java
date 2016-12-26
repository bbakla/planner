package planner.model.goal;

import planner.model.timeframe.TimeFrame;

   class GoalDetails {
	
	private GoalStatus status;
	private TimeFrame timeFrame;
	private GoalDescription description;
	
	public GoalDetails(TimeFrame timeFrame, GoalDescription description) {
		this(timeFrame, description, GoalStatus.NOT_STARTED);
	}
	
	public GoalDetails(TimeFrame timeFrame, GoalDescription description, GoalStatus status) {
		this.timeFrame = timeFrame;
		this.description = description;
		this.status = status;
	}

	public GoalStatus getStatus() {
		return status;
	}

	public void setStatus(GoalStatus status) {
		this.status = status;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public GoalDescription getDescription() {
		return description;
	}

	public void setDescription(GoalDescription description) {
		this.description = description;
	}
	
	
	
	

}
