package planner.model.timeframe;

import static org.hamcrest.CoreMatchers.instanceOf;
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
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.transaction.Transactional;

import planner.model.enums.Day;
import planner.model.enums.WeekPlannerTimeSlot;
import planner.model.goal.GoalIdentity;

@Entity
@Table(name="day_plan")
@Transactional
public class DayPlan implements Comparable<DayPlan>  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "day_plan_id", nullable = false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="day", nullable = false)
	private Day day;
	
	
	@ElementCollection(targetClass= GoalIdentity.class, fetch=FetchType.EAGER)
	@CollectionTable(name="day_plan_goals", joinColumns=@JoinColumn(name="day_plan_id"))
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name="daily_goal_id")
	private Map<WeekPlannerTimeSlot, GoalIdentity> goals = new HashMap<>();
	
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
	
	public DayPlan(Day day){
		this();
		this.day = day;
	}

	public DayPlan(Day day, Map<WeekPlannerTimeSlot, GoalIdentity> goals){
		this(day);
		this.goals = goals;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	public Day getDay() {
		return day;
	}


	public void setDay(Day day) {
		this.day = day;
	}
	
	public Map<WeekPlannerTimeSlot, GoalIdentity> getGoals() {
		return goals;
	}
	
	public void setGoals(Map<WeekPlannerTimeSlot, GoalIdentity> goals) {
		this.goals = goals;
	}
	
	public void addGoal(WeekPlannerTimeSlot slot, GoalIdentity goalId) {
		this.goals.put(slot, goalId);
	}
	
	public void removeGoal(WeekPlannerTimeSlot slot){
		this.goals.remove(slot);
	}

	@Override
	public int compareTo(DayPlan dayPlan){
		return Integer.valueOf(this.day.getDayNumber()).compareTo(dayPlan.getDay().getDayNumber());
	}
	
	@Override
	public boolean equals(final Object object){
		if(!(object instanceof DayPlan)){
			return false;
		}
		
		DayPlan dayPlan = (DayPlan) object;
		
		if(!dayPlan.getDay().name().equals(this.getDay().name())){
			return false;
		}
		
		if(!dayPlan.getGoals().equals(this.getGoals())){
			return false;
		}
		
		if(dayPlan.getId() != this.getId()){
			return false;
		}
		
		return true;
	}
	
	  @Override
      public int hashCode() {
        long hashno = 7;
        hashno = 13 * hashno + (this.getId() == null ? 0 : this.getId()) + this.goals.size();
        return (int)hashno;
      }	
}
