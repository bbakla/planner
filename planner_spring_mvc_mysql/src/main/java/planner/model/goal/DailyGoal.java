package planner.model.goal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import planner.model.goal.scope.GoalScopeNames;


@Entity
@Table(name="daily_goals")
public class DailyGoal extends Goal{
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},  fetch=FetchType.EAGER)
	private ParentGoal parentGoal;
	
	
	public DailyGoal(){
		
	}

	public DailyGoal(ParentGoal parentGoal, GoalDescription description,  int timeLabel, GoalScopeNames scope, String title) {
		super(description, timeLabel, scope, title);
		
		this.parentGoal = parentGoal;
		
		parentGoal.addChildGoal(this);
	}
	
	public ParentGoal getParentGoal() {
		return parentGoal;
	}

	public void setBelongsTo(ParentGoal parentGoal) {
		this.parentGoal = parentGoal;
	}
}
