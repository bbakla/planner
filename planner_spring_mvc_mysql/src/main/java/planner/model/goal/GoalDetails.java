package planner.model.goal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import planner.model.timeframe.TimeFrame;

//@Entity
//@Table(name="GoalDetails")
class GoalDetails {

//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
