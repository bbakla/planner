package planner.model.goal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="parent_goal")

public class ParentGoal extends Goal{
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="parent_child", joinColumns=  @JoinColumn(name="parent_goal_id"),
			   inverseJoinColumns= @JoinColumn(name="child_goal_id"))
	private List<Goal> childGoals;
	
	
	
	public ParentGoal( GoalDescription description, GoalScopeNames timeFrame, String title) {

		super(description, timeFrame, title);
		
		childGoals = new ArrayList<>();
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
