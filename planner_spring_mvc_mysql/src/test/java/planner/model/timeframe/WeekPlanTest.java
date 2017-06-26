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
import planner.dao.GenericPlanDao;
import planner.model.enums.Day;
import planner.model.enums.WeekPlannerTimeSlot;
import planner.model.goal.GoalDescription;
import planner.model.goal.GoalIdentity;
import planner.model.goal.GoalScopeNames;
import planner.model.goal.ParentGoal;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class WeekPlanTest {

	@Autowired
	@Qualifier("WeekPlanDao")
	private GenericPlanDao<WeekPlan> weekPlanDao;

	@Autowired
	@Qualifier("ParentGoalDao")
	private GenericDao<ParentGoal> dailyGoalDao;

	
	@Test
	public void shouldBeAbleToSaveWeekPlanForOneDay() {

		ParentGoal parentGoal = getWeeklyParentGoal("21", "2017");

		ParentGoal dailyGoal1 = new ParentGoal(parentGoal, null, Day.TUESDAY.toString(), GoalScopeNames.DAILY,
				"childGoal1");
		ParentGoal dailyGoal2 = new ParentGoal(parentGoal, null, Day.TUESDAY.toString(), GoalScopeNames.DAILY,
				"childGoal2");
		ParentGoal dailyGoal3 = new ParentGoal(parentGoal, null, Day.TUESDAY.toString(), GoalScopeNames.DAILY,
				"childGoal3");

		dailyGoalDao.save(parentGoal);

		DayPlan mondayPlan = getDayPlan(new GoalIdentity(dailyGoal1.getTitle(), dailyGoal1.getId()), 
				new GoalIdentity(dailyGoal2.getTitle(), dailyGoal2.getId()), 
			    new GoalIdentity(dailyGoal3.getTitle(), dailyGoal3.getId()), Day.MONDAY);

		WeekPlan weekPlan = new WeekPlan(parentGoal.getTimeUnit(),
				parentGoal.getParentGoal().getParentGoal().getTimeUnit());
		WeekPlan weekPlan2 = new WeekPlan(parentGoal.getTimeUnit(),
				parentGoal.getParentGoal().getParentGoal().getTimeUnit() + 1);

		weekPlan.addDailyPlan(mondayPlan);

		assertNull(weekPlan.getId());

		weekPlanDao.save(weekPlan);
		weekPlanDao.save(weekPlan2);
		WeekPlan savedWeeklyPlan = weekPlanDao.findById(weekPlan.getId());

		assertEquals(weekPlan.getId(), savedWeeklyPlan.getId());
	}

	@Test
	public void shouldBeAbleToSaveAWeekPlan() {
		ParentGoal parentGoal = getWeeklyParentGoal("40", "2018");

		ParentGoal dailyGoal1 = new ParentGoal(parentGoal, null, Day.TUESDAY.toString(), GoalScopeNames.DAILY,
				"childGoal1");
		ParentGoal dailyGoal2 = new ParentGoal(parentGoal, null, Day.TUESDAY.toString(), GoalScopeNames.DAILY,
				"childGoal2");
		ParentGoal dailyGoal3 = new ParentGoal(parentGoal, null, Day.TUESDAY.toString(), GoalScopeNames.DAILY,
				"childGoal3");

		dailyGoalDao.save(parentGoal);


		WeekPlan weekPlan = new WeekPlan(parentGoal.getTimeUnit(),
				parentGoal.getParentGoal().getParentGoal().getTimeUnit());
		WeekPlan weekPlan2 = new WeekPlan(parentGoal.getTimeUnit()  + 1,
				parentGoal.getParentGoal().getParentGoal().getTimeUnit());

		weekPlan.addDailyPlans(get7DaysPlan(new GoalIdentity(dailyGoal1.getTitle(), dailyGoal1.getId()), 
				                            new GoalIdentity(dailyGoal2.getTitle(), dailyGoal2.getId()),
				                            new GoalIdentity(dailyGoal3.getTitle(), dailyGoal3.getId())));
		
		DayPlan mondayPlan = getDayPlan(new GoalIdentity(dailyGoal1.getTitle(), dailyGoal1.getId()), 
				new GoalIdentity(dailyGoal2.getTitle(), dailyGoal2.getId()), 
			    new GoalIdentity(dailyGoal3.getTitle(), dailyGoal3.getId()), Day.MONDAY);
		weekPlan2.addDailyPlan(mondayPlan);

		assertNull(weekPlan.getId());

		weekPlanDao.save(weekPlan);
		weekPlanDao.save(weekPlan2);
		WeekPlan savedWeeklyPlan = weekPlanDao.findById(weekPlan.getId());

		assertEquals(weekPlan.getId(), savedWeeklyPlan.getId());
	}

	private Set<DayPlan> get7DaysPlan(GoalIdentity goalId1, GoalIdentity goalId2, GoalIdentity goalId3) {

		DayPlan monday = getDayPlan(goalId1, goalId2, goalId3, Day.MONDAY);

		DayPlan tuesday = getDayPlan(goalId2, goalId1, goalId3, Day.TUESDAY);

		DayPlan wednesday = getDayPlan(goalId3, goalId2, goalId1, Day.WEDNESDAY);

		DayPlan thursday = getDayPlan(goalId3, goalId1, goalId2, Day.THURSDAY);

		DayPlan friday = getDayPlan(goalId1, goalId2, goalId3, Day.FRIDAY);

		DayPlan saturday = getDayPlan(goalId1, goalId2, goalId3, Day.SATURDAY);

		Set<DayPlan> weeklyPlan = new HashSet<>();
		weeklyPlan.add(monday);
		weeklyPlan.add(tuesday);
		weeklyPlan.add(wednesday);
		weeklyPlan.add(thursday);
		weeklyPlan.add(friday);
		weeklyPlan.add(saturday);

		return weeklyPlan;
	}

	private DayPlan getDayPlan(GoalIdentity goalId1, GoalIdentity goalId2, GoalIdentity goalId3, Day day) {
		DayPlan dayPlan = new DayPlan(day);
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

	private ParentGoal getWeeklyParentGoal(String weekNumber, String yearNumber) {

		ParentGoal yearlyGoal = new ParentGoal(null, yearNumber, GoalScopeNames.YEARLY, "yearlyGoal");
		ParentGoal monthlyGoal = new ParentGoal(yearlyGoal, null, weekNumber, GoalScopeNames.MONTHLY, "monthlyGoal");

		GoalDescription description = new GoalDescription("Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		ParentGoal weeklyGoal = new ParentGoal(monthlyGoal, description, weekNumber, GoalScopeNames.WEEKLY, "parentGoal1");

		return weeklyGoal;
	}

}
