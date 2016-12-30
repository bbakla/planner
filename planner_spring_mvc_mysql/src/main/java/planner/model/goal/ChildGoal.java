package planner.model.goal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import planner.model.goal.scope.GoalScope;
import planner.model.goal.scope.GoalScopeNames;


@Entity
@Table(name="child_goal")
public class ChildGoal extends Goal{
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},  fetch=FetchType.EAGER)
	private Goal parentGoal;
	

	public ChildGoal(Goal parentGoal, GoalScope scope, GoalDescription description, String title) {
		super(description, scope, title);
		
		this.parentGoal = parentGoal;
	}
	
	public Goal getParentGoal() {
		return parentGoal;
	}

	public void setBelongsTo(Goal parentGoal) {
		this.parentGoal = parentGoal;
	}
}
