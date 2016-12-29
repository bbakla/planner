//package planner.model.goal;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//
//@Entity
//@Table(name="child_goal")
//public class ChildGoal extends Goal{
//	
////	@Id
////	@GeneratedValue(strategy=GenerationType.AUTO)
////	@Column(name="child_goal_id", nullable = false)
////	private Long id;
//	
//	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},  fetch=FetchType.EAGER)
////	@JoinColumn(name="goal_id")
//	private Goal parentGoal;
//	
//	
//
//	public ChildGoal(Goal parentGoal, GoalScopeNames timeFrame, GoalDescription description) {
//		super(description, timeFrame);
//		
//		this.parentGoal = parentGoal;
//	}
//	
//	public Goal getParentGoal() {
//		return parentGoal;
//	}
//
//	public void setBelongsTo(Goal parentGoal) {
//		this.parentGoal = parentGoal;
//	}
//}
