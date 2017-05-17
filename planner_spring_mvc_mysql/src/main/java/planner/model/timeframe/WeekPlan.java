package planner.model.timeframe;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "weekPlan")
public class WeekPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_id")
	protected Long id;

	@Column
	private int yearNumber;

	@Column
	private int weekNumber;

	@OneToMany(cascade=CascadeType.ALL)	
	@JoinColumn(name="weekPlan_id")
	private Map<String, DayPlan> weekPlan = new HashMap<>();

	public WeekPlan() {
		weekPlan.put("Monday", null);
		weekPlan.put("Tuesday", null);
		weekPlan.put("Wednesday", null);
		weekPlan.put("Thursday", null);
		weekPlan.put("Friday", null);
		weekPlan.put("Saturday", null);
		weekPlan.put("Sunday", null);

		LocalDate currentDate = LocalDate.now();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		
		this.yearNumber = currentDate.getYear();
		
		this.weekNumber = currentDate.get(woy);
	}

	public WeekPlan(int weekNumber, int yearNumber) {

		this();
		this.yearNumber = yearNumber;
		this.weekNumber = weekNumber;
	}

	public Map<String, DayPlan> getWeekPlan() {
		return weekPlan;
	}

	public Long getId(){
		return id;
	}
	
	public void addDailyPlan(String day, DayPlan dayPlan) {
		weekPlan.put(day, dayPlan);
	}
}
