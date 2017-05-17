package planner.model.timeframe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.poifs.property.Parent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import planner.dao.GenericDao;
import planner.model.goal.GoalDescription;
import planner.model.goal.GoalDetails;
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
	public void shouldBeAbleToSaveWeekPlan(){
		
		ParentGoal parentGoal = getWeeklyParentGoal();
		
		ParentGoal dailyGoal1 = new ParentGoal(parentGoal, null,  Calendar.MONDAY + 1, GoalScopeNames.DAILY, "childGoal1");
		ParentGoal dailyGoal2 = new ParentGoal(parentGoal, null,  Calendar.MONDAY + 1, GoalScopeNames.DAILY, "childGoal2");
		ParentGoal dailyGoal3 = new ParentGoal(parentGoal, null,  Calendar.MONDAY + 1, GoalScopeNames.DAILY, "childGoal3");
		
		dailyGoalDao.save(parentGoal);
		dailyGoalDao.save(dailyGoal1);
		dailyGoalDao.save(dailyGoal2);
		dailyGoalDao.save(dailyGoal3);
		
		DayPlan mondayPlan = new DayPlan();
//		mondayPlan.addGoal(WeekPlannerTimeSlot.TILL_9, dailyGoal1.getId());
//		mondayPlan.addGoal(WeekPlannerTimeSlot.TILL_10, dailyGoal1.getId());
//		mondayPlan.addGoal(WeekPlannerTimeSlot.TILL_11, dailyGoal1.getId());
//		mondayPlan.addGoal(WeekPlannerTimeSlot.TILL_12, dailyGoal1.getId());
//		mondayPlan.addGoal(WeekPlannerTimeSlot.TILL_13, dailyGoal2.getId());
//		mondayPlan.addGoal(WeekPlannerTimeSlot.TILL_14, dailyGoal2.getId());
//		mondayPlan.addGoal(WeekPlannerTimeSlot.TILL_15, dailyGoal2.getId());
//		mondayPlan.addGoal(WeekPlannerTimeSlot.TILL_17, dailyGoal2.getId());
//		mondayPlan.addGoal(WeekPlannerTimeSlot.AFTER_17, dailyGoal3.getId());
		
		WeekPlan weekPlan = new WeekPlan(parentGoal.getTimeLabel(), parentGoal.getParentGoal().getParentGoal().getTimeLabel());
//		weekPlan.addDailyPlan("Monday", mondayPlan);
		
		assertNull(weekPlan.getId());
		
		weekPlanDao.save(weekPlan);
		WeekPlan savedWeeklyPlan = weekPlanDao.findById(weekPlan.getId());
		
		assertEquals(weekPlan.getId(), savedWeeklyPlan.getId());
	}
	
	private ParentGoal getWeeklyParentGoal(){
		
		ParentGoal yearlyGoal = new ParentGoal(null, 2017, GoalScopeNames.YEARLY, "yearlyGoal");
		ParentGoal monthlyGoal = new ParentGoal(yearlyGoal, null, 5, GoalScopeNames.MONTHLY, "monthlyGoal");
		
		
		GoalDescription description = new GoalDescription( "Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		ParentGoal weeklyGoal = new ParentGoal(monthlyGoal, description, 20, GoalScopeNames.WEEKLY, "parentGoal1");
		
		return weeklyGoal;
	}

}
