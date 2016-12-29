package planner.model.goal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="parent_goal")

public class ParentGoal extends Goal{
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="parent_goal_id", nullable = false)
//	private Long id;
	
//	@ElementCollection(fetch=FetchType.EAGER)
//	@CollectionTable(name="child_goals", joinColumns=@JoinColumn(name="Id", referencedColumnName="Id"))
//	@Column(name="Comment")
//	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<Goal> childGoals;
	
	
	
	public ParentGoal( GoalDescription description, GoalScopeNames timeFrame) {

		super(description, timeFrame);
//		childGoals = new ArrayList<>();
	}


//	public List<Goal> getChildGoals() {
//		return childGoals;
//	}
//
//	public void setChildGoals(List<Goal> childGoals) {
//		this.childGoals = childGoals;
//	}
//	
//	public void addChildGoal(Goal goal){
//		childGoals.add(goal);
//	}
}
