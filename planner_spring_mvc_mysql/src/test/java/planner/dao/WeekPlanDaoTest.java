package planner.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import planner.model.enums.WeekPlannerTimeSlot;
import planner.model.goal.GoalDescription;
import planner.model.goal.GoalScopeNames;
import planner.model.goal.ParentGoal;
import planner.model.timeframe.DayPlan;
import planner.model.timeframe.WeekPlan;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class WeekPlanDaoTest {

	@Autowired
	@Qualifier("WeekPlanDao")
	private GenericPlanDao<WeekPlan> weekPlanDao;

	@Autowired
	@Qualifier("ParentGoalDao")
	private GenericDao<ParentGoal> dailyGoalDao;
	
	private final int weekNumber = 20;
	private final int month = 5;
	private final int yearNumber = 2017;

	@Before
	public void setUp() {
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
	}
	
	@Test
	public void searchWeekPlanDueToYearAndWeekNumber(){
		
		WeekPlan weekPlan = weekPlanDao.findByTimeLabel(yearNumber, weekNumber);
		
		assertEquals(weekNumber, weekPlan.getWeekNumber());
		assertEquals(yearNumber, weekPlan.getYearNumber());
		assertNotNull(weekPlan.getId());
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

		ParentGoal yearlyGoal = new ParentGoal(null, yearNumber, GoalScopeNames.YEARLY, "yearlyGoal");
		ParentGoal monthlyGoal = new ParentGoal(yearlyGoal, null, month, GoalScopeNames.MONTHLY, "monthlyGoal");

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
