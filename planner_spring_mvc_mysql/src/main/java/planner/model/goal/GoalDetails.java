package planner.model.goal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import planner.model.goal.scope.GoalScope;
import planner.model.goal.scope.GoalScopeNames;
import planner.model.timeframe.Planner;

@Entity
@Table(name="goal_details")
public class GoalDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="goal_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private GoalStatus status;
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@PrimaryKeyJoinColumn
	@Embedded
	private GoalScope scope;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private GoalDescription description;
	
	public GoalDetails(GoalDescription description, GoalScope scope) {
		this(description, scope, GoalStatus.NOT_STARTED);
	}

	public GoalDetails(GoalDescription description, GoalScope scope, GoalStatus status) {
		this.scope = scope;
		this.description = description;
		this.status = status;
	}

	public GoalStatus getStatus() {
		return status;
	}

	public void setStatus(GoalStatus status) {
		this.status = status;
	}

	public GoalScope getTimeFrame() {
		return scope;
	}

	public void setTimeFrame(GoalScope timeFrame) {
		this.scope = timeFrame;
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
