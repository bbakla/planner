package planner.model.goal.scope;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class GoalScope {
	
	private int timeLabel;
	
	@Enumerated(EnumType.STRING)
	private GoalScopeNames scope;
	
	public GoalScope() {
	}
	
	public GoalScope(int timeLabel, GoalScopeNames scope) {
		super();
		this.timeLabel = timeLabel;
		this.scope = scope;
	}
	public int getTimeLabel() {
		return timeLabel;
	}
	public void setTimeLabel(int timeLabel) {
		this.timeLabel = timeLabel;
	}
	public GoalScopeNames getScope() {
		return scope;
	}
	public void setScope(GoalScopeNames scope) {
		this.scope = scope;
	}
	
	
	}
