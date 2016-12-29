package planner.model.goal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import planner.model.timeframe.GoalScope;

@Entity
@Table(name="goal_details")
public class GoalDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="detail_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private GoalStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(name="scope")
	private GoalScopeNames scope;
	
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@PrimaryKeyJoinColumn
//	private GoalDescription description;
	
	public GoalDetails(){
		this.status = GoalStatus.NOT_STARTED;
		this.scope = GoalScopeNames.YEARLY;
	}

	public GoalDetails(GoalDescription description, GoalScopeNames timeFrame) {
		this(description, timeFrame, GoalStatus.NOT_STARTED);
	}

	public GoalDetails(GoalDescription description, GoalScopeNames timeFrame, GoalStatus status) {
		this.scope = timeFrame;
//		this.description = description;
		this.status = status;
	}

	public GoalStatus getStatus() {
		return status;
	}

	public void setStatus(GoalStatus status) {
		this.status = status;
	}

	public GoalScopeNames getTimeFrame() {
		return scope;
	}

	public void setTimeFrame(GoalScopeNames timeFrame) {
		this.scope = timeFrame;
	}

//	public GoalDescription getDescription() {
//		return description;
//	}
//
//	public void setDescription(GoalDescription description) {
//		this.description = description;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
