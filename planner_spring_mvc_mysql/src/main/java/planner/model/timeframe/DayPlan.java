package planner.model.timeframe;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name="day_plan")
@Transactional
public class DayPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_id")
	protected Long id;
	
//	@OneToMany(cascade=CascadeType.ALL)	
////	@JoinColumn(name="weekPlan_id")
//	@MapKeyEnumerated(EnumType.ORDINAL)
//	private Map<WeekPlannerTimeSlot, Long> goals = new HashMap<>();
	
	public DayPlan()
		{
//			goals.put(WeekPlannerTimeSlot.TILL_9, 0L);
//			goals.put(WeekPlannerTimeSlot.TILL_10, 0L);
//			goals.put(WeekPlannerTimeSlot.TILL_11, 0L);
//			goals.put(WeekPlannerTimeSlot.TILL_12, 0L);
//			goals.put(WeekPlannerTimeSlot.TILL_13, 0L);
//			goals.put(WeekPlannerTimeSlot.TILL_14 ,0L);
//			goals.put(WeekPlannerTimeSlot.TILL_15, 0L);
//			goals.put(WeekPlannerTimeSlot.TILL_16, 0L);
//			goals.put(WeekPlannerTimeSlot.TILL_17, 0L);
//			goals.put(WeekPlannerTimeSlot.AFTER_17, 0L);
	}

//	public Map<WeekPlannerTimeSlot, Long> getGoals() {
//		return goals;
//	}
//	
//	public void addGoal(WeekPlannerTimeSlot slot, Long goalId) {
//		this.goals.put(slot, goalId);
//	}
//	
//	public void removeGoal(WeekPlannerTimeSlot slot){
//		this.goals.remove(slot);
//	}
}
