package planner.model.goal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="parent_goal")

public class ParentGoal extends Goal implements Serializable{
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="parent_child", joinColumns=  @JoinColumn(name="parent_goal_id"),
			   inverseJoinColumns= @JoinColumn(name="child_goal_id"))
	private List<Goal> childGoals;
	
	@OneToOne(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name= "goal_id", nullable = true)
	private ParentGoal parentGoal;
	
	public ParentGoal(){
		childGoals = new ArrayList<>();
	}
	
	public ParentGoal(GoalDescription description, int timeLabel, GoalScopeNames scope, String title) {

		this(null, description, timeLabel, scope, title);
	}
	
	public ParentGoal(ParentGoal parentGoal, GoalDescription description, int timeLabel, GoalScopeNames scope, String title) {

		super(description, timeLabel, scope, title);
		
		childGoals = new ArrayList<>();
		
		if(parentGoal != null){
			this.parentGoal = parentGoal;
			parentGoal.addChildGoal(this);
		}
			
		
	}
	
	public List<Goal> getChildGoals() {
		return childGoals;
	}

	public void setChildGoals(List<Goal> childGoals) {
		this.childGoals = childGoals;
	}
	
	public void addChildGoal(Goal goal){
		if(!(goal != null && childGoals.contains(goal))){
			childGoals.add(goal);
		}
		
	}

	public ParentGoal getParentGoal() {
		return parentGoal;
	}

	public void setParentGoal(ParentGoal parentGoal) {
		this.parentGoal = parentGoal;
		parentGoal.addChildGoal(this);
	}
	
	
	
	
}
