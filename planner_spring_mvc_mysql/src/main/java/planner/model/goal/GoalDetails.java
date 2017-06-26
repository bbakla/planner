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

@Entity
@Table(name="goal_details")
public class GoalDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="goal_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable = false)
	private GoalStatus status;
	
	@Column(name= "time_label", nullable= false)
	private int timeLabel;
	
	@Enumerated(EnumType.STRING)
	private GoalScopeNames scope;
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private GoalDescription description;
	
	public GoalDetails(){
		
	}
	
	public GoalDetails(GoalDescription description, int timeLabel, GoalScopeNames scope) {
		this(description, timeLabel, scope, GoalStatus.NOT_STARTED);
	}

	public GoalDetails(GoalDescription description, int timeLabel, GoalScopeNames scope, GoalStatus status) {
		this.scope = scope;
		this.description = description;
		this.status = status;
		this.timeLabel = timeLabel;
	}

	public GoalStatus getStatus() {
		return status;
	}

	public void setStatus(GoalStatus status) {
		this.status = status;
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
