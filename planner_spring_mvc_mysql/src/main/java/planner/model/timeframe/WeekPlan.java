package planner.model.timeframe;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "week_plan", uniqueConstraints = @UniqueConstraint(columnNames = {"yearNumber", "weekNumber"}))
public class WeekPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "week_plan_id")
	protected Long id;

	@Column
	private int yearNumber;

	@Column
	private int weekNumber;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "WEEK_DAY_PLAN", joinColumns = { @JoinColumn(name = "week_plan_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "day_plan_id") })
	private Set<DayPlan> weekPlan = new HashSet<>();

	public WeekPlan() {

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
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public int getYearNumber() {
		return yearNumber;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void addDailyPlan(DayPlan dayPlan) {
		weekPlan.add(dayPlan);
	}
	
	public void addDailyPlans(Set<DayPlan> dayPlans){
		this.weekPlan.addAll(dayPlans);
	}

	public Set<DayPlan> getWeekPlan() {
		return weekPlan;
	}

	public void setWeekPlan(Set<DayPlan> weekPlan) {
		this.weekPlan = weekPlan;
	}
	
	
}
