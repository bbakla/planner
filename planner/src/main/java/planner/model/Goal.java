package planner.model;

import planner.model.timeframe.TimeFrame;

public class Goal {
	
	private GoalStatus status;
	private Goal belongsTo;
	private TimeFrame timeFrame;
	private GoalDescription description;
	
	public Goal(Goal belongsTo, TimeFrame timeFrame, GoalDescription description) {
		this(belongsTo, timeFrame, description, GoalStatus.NOT_STARTED);
	}
	
	public Goal(Goal belongsTo, TimeFrame timeFrame, GoalDescription description, GoalStatus status) {
		this.belongsTo = belongsTo;
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

	public Goal getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(Goal belongsTo) {
		this.belongsTo = belongsTo;
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
