package planner.model.goal.progress;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import planner.model.goal.Goal;

@Entity
@Table(name="progress")
public class GoalProgress{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="goal_id")
	private Long id;
	
	@Column
	private boolean completed;
	
	@Column
	private double progressPercentage;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public double getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(double progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public void calculateProgress(List<Goal> items) {
	
		int numberOfCompletedTasks = 0;
		
		for(Goal item : items){
			if(item.getProgress().isCompleted()){
				numberOfCompletedTasks++;
			}
		}
		
		progressPercentage =  numberOfCompletedTasks * 100 / items.size();
	}
}
