package planner.model.timeframe;

import static org.junit.Assert.fail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.transaction.Transactional;

import planner.model.enums.WeekPlannerTimeSlot;

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
	
	
	@ElementCollection(targetClass= Long.class, fetch=FetchType.EAGER)
	@CollectionTable(name="day_plan_goals", joinColumns=@JoinColumn(name="day_plan_id"))
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name="daily_goal_id")
	private Map<WeekPlannerTimeSlot, Long> goals = new HashMap<>();
	
	public DayPlan(){
		goals.put(WeekPlannerTimeSlot.TILL_9, null);
		goals.put(WeekPlannerTimeSlot.TILL_10, null);
		goals.put(WeekPlannerTimeSlot.TILL_11, null);
		goals.put(WeekPlannerTimeSlot.TILL_12, null);
		goals.put(WeekPlannerTimeSlot.TILL_13, null);
		goals.put(WeekPlannerTimeSlot.TILL_14, null);
		goals.put(WeekPlannerTimeSlot.TILL_15, null);
		goals.put(WeekPlannerTimeSlot.TILL_16, null);
		goals.put(WeekPlannerTimeSlot.TILL_17, null);
		goals.put(WeekPlannerTimeSlot.AFTER_17, null);
	}

	public DayPlan(String day, Map<WeekPlannerTimeSlot, Long> goals){
		this();
		this.day = day;
		this.goals = goals;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

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
