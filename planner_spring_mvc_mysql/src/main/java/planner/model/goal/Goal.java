package planner.model.goal;

import java.io.Serializable;

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

import planner.model.goal.progress.GoalProgress;

@Entity
@Table(name = "goal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Goal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @SequenceGenerator(name="goal_generator", sequenceName = "goal_seq",
	// initialValue=1, allocationSize=5000)
	@Column(name = "goal_id")
	protected Long id;

	@Column(name = "title", nullable = false)
	protected String title;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	protected GoalDetails details;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	protected GoalProgress progress;

	public Goal() {
		this.progress = new GoalProgress();
	}

	public Goal(GoalDescription description, String timeUnit, GoalScopeNames scope, String title) {
		
		this();
		
		this.details = new GoalDetails(description, timeUnit, scope);
		this.title = title;
	}

	public void setStatus(GoalStatus status) {
		details.setStatus(status);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDetails(GoalDetails details) {
		this.details = details;
	}

	public GoalDetails getDetails() {
		return this.details;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setScope(GoalScopeNames scope) {
		this.details.setScope(scope);
	}
	
	

	public GoalProgress getProgress() {
		return progress;
	}

	public void setProgress(GoalProgress progress) {
		this.progress = progress;
	}

	public String getTimeUnit() {
		return details.getTimeUnit();
	}
	
	public void setTimeUnit(String timeUnit){
		details.setTimeUnit(timeUnit);
	}
}
