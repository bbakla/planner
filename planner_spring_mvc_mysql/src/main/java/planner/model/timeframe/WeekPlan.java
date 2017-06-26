package planner.model.timeframe;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "week_plan", uniqueConstraints = @UniqueConstraint(columnNames = {"yearNumber", "weekNumber"}))
public class WeekPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "week_plan_id")
	protected Long id;

	@Column
	private String yearNumber;

	@Column
	private String weekNumber;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "WEEK_DAY_PLAN", joinColumns = { @JoinColumn(name = "week_plan_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "day_plan_id") })
	private List<DayPlan> weekPlan = new ArrayList<DayPlan>();

	public WeekPlan() {

		LocalDate currentDate = LocalDate.now();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
		
		this.yearNumber = Integer.toString(currentDate.getYear());
		
		this.weekNumber = Integer.toString(currentDate.get(woy));
	}

	public WeekPlan(String weekNumber, String yearNumber) {

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

	public String getYearNumber() {
		return yearNumber;
	}

	public String getWeekNumber() {
		return weekNumber;
	}

	public void addDailyPlan(DayPlan dayPlan) {
		weekPlan.add(dayPlan);
	}
	
	public void addDailyPlans(Set<DayPlan> dayPlans){
		this.weekPlan.addAll(dayPlans);
	}

	public List<DayPlan> getWeekPlan() {
		return weekPlan;
	}

	public void setWeekPlan(List<DayPlan> weekPlan) {
		this.weekPlan = weekPlan;
	}
	
	
}
