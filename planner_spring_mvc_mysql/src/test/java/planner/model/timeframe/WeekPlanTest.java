package planner.model.timeframe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import planner.dao.GenericDao;
import planner.model.goal.GoalDescription;
import planner.model.goal.GoalScopeNames;
import planner.model.goal.ParentGoal;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class WeekPlanTest {

	@Autowired
	@Qualifier("WeekPlanDao")
	private GenericDao<WeekPlan> weekPlanDao;

	@Autowired
	@Qualifier("ParentGoalDao")
	private GenericDao<ParentGoal> dailyGoalDao;

	@Test
	public void shouldBeAbleToSaveWeekPlanForOneDay() {

		ParentGoal parentGoal = getWeeklyParentGoal();

		ParentGoal dailyGoal1 = new ParentGoal(parentGoal, null, Calendar.MONDAY + 1, GoalScopeNames.DAILY,
				"childGoal1");
		ParentGoal dailyGoal2 = new ParentGoal(parentGoal, null, Calendar.MONDAY + 1, GoalScopeNames.DAILY,
				"childGoal2");
		ParentGoal dailyGoal3 = new ParentGoal(parentGoal, null, Calendar.MONDAY + 1, GoalScopeNames.DAILY,
				"childGoal3");

		dailyGoalDao.save(parentGoal);

		DayPlan mondayPlan = getDayPlan(dailyGoal1.getId(), dailyGoal2.getId(), dailyGoal3.getId());
		mondayPlan.setDay("Monday");

		WeekPlan weekPlan = new WeekPlan(parentGoal.getTimeLabel(),
				parentGoal.getParentGoal().getParentGoal().getTimeLabel());
		WeekPlan weekPlan2 = new WeekPlan(parentGoal.getTimeLabel(),
				parentGoal.getParentGoal().getParentGoal().getTimeLabel() + 1);

		weekPlan.addDailyPlan(mondayPlan);

		assertNull(weekPlan.getId());

		weekPlanDao.save(weekPlan);
		weekPlanDao.save(weekPlan2);
		WeekPlan savedWeeklyPlan = weekPlanDao.findById(weekPlan.getId());

		assertEquals(weekPlan.getId(), savedWeeklyPlan.getId());
	}

	@Test
	public void shouldBeAbleToSaveAWeekPlan() {
		ParentGoal parentGoal = getWeeklyParentGoal();

		ParentGoal dailyGoal1 = new ParentGoal(parentGoal, null, Calendar.MONDAY + 1, GoalScopeNames.DAILY,
				"childGoal1");
		ParentGoal dailyGoal2 = new ParentGoal(parentGoal, null, Calendar.MONDAY + 1, GoalScopeNames.DAILY,
				"childGoal2");
		ParentGoal dailyGoal3 = new ParentGoal(parentGoal, null, Calendar.MONDAY + 1, GoalScopeNames.DAILY,
				"childGoal3");

		dailyGoalDao.save(parentGoal);


		WeekPlan weekPlan = new WeekPlan(parentGoal.getTimeLabel(),
				parentGoal.getParentGoal().getParentGoal().getTimeLabel());
		WeekPlan weekPlan2 = new WeekPlan(parentGoal.getTimeLabel()  + 1,
				parentGoal.getParentGoal().getParentGoal().getTimeLabel());

		weekPlan.addDailyPlans(get7DaysPlan(dailyGoal1.getId(), dailyGoal2.getId(), dailyGoal3.getId()));
		
		DayPlan mondayPlan = getDayPlan(dailyGoal1.getId(), dailyGoal2.getId(), dailyGoal3.getId());
		mondayPlan.setDay("Monday");
		weekPlan2.addDailyPlan(mondayPlan);

		assertNull(weekPlan.getId());

		weekPlanDao.save(weekPlan);
		weekPlanDao.save(weekPlan2);
		WeekPlan savedWeeklyPlan = weekPlanDao.findById(weekPlan.getId());

		assertEquals(weekPlan.getId(), savedWeeklyPlan.getId());
	}

	private Set<DayPlan> get7DaysPlan(Long goalId1, Long goalId2, Long goalId3) {

		DayPlan monday = getDayPlan(goalId1, goalId2, goalId3);
		monday.setDay("Monday");

		DayPlan tuesday = getDayPlan(goalId2, goalId1, goalId3);
		tuesday.setDay("Tuesday");

		DayPlan wednesday = getDayPlan(goalId3, goalId2, goalId1);
		wednesday.setDay("Wednesday");

		DayPlan thursday = getDayPlan(goalId3, goalId1, goalId2);
		thursday.setDay("Thursday");

		DayPlan friday = getDayPlan(goalId1, goalId2, goalId3);
		friday.setDay("Friday");

		DayPlan saturday = getDayPlan(goalId1, goalId2, goalId3);
		saturday.setDay("Monday");

		Set<DayPlan> weeklyPlan = new HashSet<>();
		weeklyPlan.add(monday);
		weeklyPlan.add(tuesday);
		weeklyPlan.add(wednesday);
		weeklyPlan.add(thursday);
		weeklyPlan.add(friday);
		weeklyPlan.add(saturday);

		return weeklyPlan;
	}

	private DayPlan getDayPlan(Long goalId1, Long goalId2, Long goalId3) {
		DayPlan dayPlan = new DayPlan();
		dayPlan.addGoal(WeekPlannerTimeSlot.TILL_9, goalId1);
		dayPlan.addGoal(WeekPlannerTimeSlot.TILL_10, goalId1);
		dayPlan.addGoal(WeekPlannerTimeSlot.TILL_11, goalId1);
		dayPlan.addGoal(WeekPlannerTimeSlot.TILL_12, goalId1);
		dayPlan.addGoal(WeekPlannerTimeSlot.TILL_13, goalId2);
		dayPlan.addGoal(WeekPlannerTimeSlot.TILL_14, goalId2);
		dayPlan.addGoal(WeekPlannerTimeSlot.TILL_15, goalId2);
		dayPlan.addGoal(WeekPlannerTimeSlot.TILL_17, goalId2);
		dayPlan.addGoal(WeekPlannerTimeSlot.AFTER_17, goalId3);

		return dayPlan;
	}

	private ParentGoal getWeeklyParentGoal() {

		ParentGoal yearlyGoal = new ParentGoal(null, 2017, GoalScopeNames.YEARLY, "yearlyGoal");
		ParentGoal monthlyGoal = new ParentGoal(yearlyGoal, null, 5, GoalScopeNames.MONTHLY, "monthlyGoal");

		GoalDescription description = new GoalDescription("Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		ParentGoal weeklyGoal = new ParentGoal(monthlyGoal, description, 20, GoalScopeNames.WEEKLY, "parentGoal1");

		return weeklyGoal;
	}

}
