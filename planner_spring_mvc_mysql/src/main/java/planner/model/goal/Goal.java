package planner.model.goal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="goal")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Goal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="goal_id", nullable = false)
	protected Long id;
	
	@Column(name="title")
	protected String title;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	protected GoalDetails details;
	
	public Goal(GoalDescription description, GoalScopeNames scope, String title) {
		this.details = new GoalDetails(description, scope);
		this.title = title;
	}
	
	public void setStatus(GoalStatus status) {
		details.setStatus(status);
		
	}

	public GoalScopeNames getTimeFrame() {
		return details.getTimeFrame();
	}

	public void setTimeFrame(GoalScopeNames timeFrame) {
		details.setTimeFrame(timeFrame);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GoalDetails getDetails() {
		return details;
	}

	public void setDetails(GoalDetails details) {
		this.details = details;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

//	public GoalDescription getDescription() {
//		return details.getDescription();
//	}
//
//	public void setDescription(GoalDescription description) {
//		details.setDescription(description);
//	}
	
	
}
