package planner.model.timeframe;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name="day_plan")
@Transactional
public class DayPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "day_plan_id", nullable = false)
	private Long id;
	
	@Column(name="day", nullable = false)
	private String day;
	
/*	@ElementCollection(targetClass= Long.class)
	@MapKeyEnumerated(EnumType.ORDINAL)
	*/

	@ElementCollection(targetClass= Long.class)
	@CollectionTable(name="day_plan_goals", joinColumns=@JoinColumn(name="day_plan_id"))
	@MapKeyEnumerated(EnumType.ORDINAL)
	@Column(name="daily_goal_id")
	private Map<WeekPlannerTimeSlot, Long> goals = new HashMap<>();
	

	public Long getId() {
		return id;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}
	
	public Map<WeekPlannerTimeSlot, Long> getGoals() {
		return goals;
	}
	
	public void addGoal(WeekPlannerTimeSlot slot, Long goalId) {
		this.goals.put(slot, goalId);
	}
	
	public void removeGoal(WeekPlannerTimeSlot slot){
		this.goals.remove(slot);
	}
}
