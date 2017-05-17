package planner.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import planner.dao.GenericDao;
import planner.model.goal.Goal;
import planner.model.goal.GoalDescription;
import planner.model.goal.GoalScopeNames;
import planner.model.goal.ParentGoal;
import planner.test.config.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ParentGoalDaoTest {

	@Autowired
	@Qualifier("ParentGoalDao")
	private GoalGenericDao<Goal> dao;
	
	@Before
	public void prepare(){
		List<Goal> goals = dao.findAll();
		
		goals.forEach(goal-> dao.delete(goal));
	}
	
	/*
	public void set(){
		GoalDescription description = new GoalDescription( "Wir müssen unserem Leben beherrschen");
		List<String> comments = new ArrayList<>();
		comments.add("erobern");
		comments.add("erben");
		comments.add("wir müssen die Regeln einhalten");
		description.setComments(comments);
		
		GoalDescription descriptionForMonth = new GoalDescription( "Er will die Regime verklagen.");
		List<String> commentsForChilds = new ArrayList<>();
		commentsForChilds.add("erhalten");
		commentsForChilds.add("einhalten");
		commentsForChilds.add("in Erinnerung behalten");
		descriptionForMonth.setComments(commentsForChilds);
		
		GoalDescription descriptionForDaily = new GoalDescription( "Er will die Regime verklagen_rrrr.");
		List<String> commentsForChilds2 = new ArrayList<>();
		commentsForChilds2.add("erhalten_rrr");
		commentsForChilds2.add("einhalten_rrr");
		commentsForChilds2.add("in Erinnerung behalten_rrr");
		descriptionForDaily.setComments(commentsForChilds2);
		
		
		ParentGoal yearlyGoal = new ParentGoal(description, 2016, GoalScopeNames.YEARLY, "yearly_parentGoal1");
		
		ParentGoal monthlyGoal = new ParentGoal(descriptionForMonth, Calendar.APRIL, GoalScopeNames.MONTHLY, "monthly_parentGoal1");
		monthlyGoal.setParentGoal(yearlyGoal);
		
		Goal dailyGoal = new ParentGoal(monthlyGoal, descriptionForDaily,  Calendar.MONDAY, GoalScopeNames.MONTHLY, "dailychildGoal1");
		
		
		yearlyGoal.addChildGoal(monthlyGoal);
//		yearlyGoal.addChildGoal(weeklyGoal);
		dao.save(yearlyGoal);
	}
	*/
	
	@Test
	public void daoShouldBeAbleToFindOnlyYearlyGoals(){
		
		
//		GoalDescription description = new GoalDescription( "Alles muss ausreichen sein.");
//		List<String> comments = new ArrayList<>();
//		comments.add("Er hat unseres Raum ausgestatt");
//		description.setComments(comments);
//		
//		ParentGoal yearlyGoal = new ParentGoal(description, 2017, GoalScopeNames.YEARLY, "yearly_parentGoal_2017_________");
//		ParentGoal yearlyGoal2 = new ParentGoal(new GoalDescription("Ých will mich mit andere Fremdsprache austatten"), 
//				2016, GoalScopeNames.YEARLY, "yearly_parentGoal_2016");
		
	//	dao.save(yearlyGoal);
		//dao.save(yearlyGoal2);
		
//		assertEquals(2, dao.findAll().size());
		
//		List<Goal> yearlyGoalsInDatabase = dao.findByTimeLabel(yearlyGoal.getTimeLabel()); 
//		assertEquals(1, yearlyGoalsInDatabase.size());
//		assertEquals(yearlyGoal.getTitle(), yearlyGoalsInDatabase.get(0).getTitle());
	}
	
	@Test
	public void daoShouldBeAbleToFindMonthlyGoalsOfGivenGoals() throws Exception{
		
		
		ParentGoal yearlyGoal = new ParentGoal(new GoalDescription(), 2017, GoalScopeNames.YEARLY, "yearly_parentGoal_2017");
		ParentGoal yearlyGoal2 = new ParentGoal(new GoalDescription("Ých will mich mit andere Fremdsprache austatten"), 
				2016, GoalScopeNames.YEARLY, "yearly_parentGoal_2016");
		
		dao.save(yearlyGoal);
		dao.save(yearlyGoal2);
		
		ParentGoal monthlyGoal = new ParentGoal(yearlyGoal, new GoalDescription(), 0, GoalScopeNames.MONTHLY, "monthly_parentGoal_jan");
		ParentGoal monthlyGoal2 = new ParentGoal(yearlyGoal, new GoalDescription(), 0, GoalScopeNames.MONTHLY, "monthly_parentGoal_jan2");
		ParentGoal monthlyGoal3 = new ParentGoal(yearlyGoal, new GoalDescription(), 1, GoalScopeNames.MONTHLY, "monthly_parentGoal_feb");
		ParentGoal monthlyGoal4 = new ParentGoal(yearlyGoal2, new GoalDescription(), 0, GoalScopeNames.MONTHLY, "monthly_parentGoal_feb2");
		
		dao.save(monthlyGoal);
		dao.save(monthlyGoal2);
		dao.save(monthlyGoal3);
		dao.save(monthlyGoal4);
		
		List<Goal> januaryGoalsOf2017 = dao.findMonthlyGoals(yearlyGoal.getTimeLabel(), 0);
		
		assertEquals(2, januaryGoalsOf2017.size());
		assertEquals(monthlyGoal.getTitle(), januaryGoalsOf2017.get(0).getTitle());
		
	}
	
	@Test
	public void daoShouldBeAbleToFindWeeklyGoalsOfGivenMonth() throws Exception{
		
		ParentGoal yearlyGoal = new ParentGoal(new GoalDescription(), 2017, GoalScopeNames.YEARLY, "yearly_parentGoal_2017");
		ParentGoal yearlyGoal2 = new ParentGoal(new GoalDescription("Ých will mich mit andere Fremdsprache austatten"), 
				2016, GoalScopeNames.YEARLY, "yearly_parentGoal_2016");
		
		dao.save(yearlyGoal);
		dao.save(yearlyGoal2);
		
		ParentGoal monthlyGoal = new ParentGoal(yearlyGoal, new GoalDescription(), 0, GoalScopeNames.MONTHLY, "monthly_parentGoal_jan");
		ParentGoal monthlyGoal2 = new ParentGoal(yearlyGoal, new GoalDescription(), 0, GoalScopeNames.MONTHLY, "monthly_parentGoal_jan2");
		ParentGoal monthlyGoal3 = new ParentGoal(yearlyGoal, new GoalDescription(), 1, GoalScopeNames.MONTHLY, "monthly_parentGoal_feb");
		ParentGoal monthlyGoal4 = new ParentGoal(yearlyGoal2, new GoalDescription(), 0, GoalScopeNames.MONTHLY, "monthly_parentGoal_feb2");
		
		dao.save(monthlyGoal);
		dao.save(monthlyGoal2);
		dao.save(monthlyGoal3);
		dao.save(monthlyGoal4);
		
		ParentGoal weeklyGoal = new ParentGoal(monthlyGoal, new GoalDescription(), 1, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan");
		ParentGoal weeklyGoal2 = new ParentGoal(monthlyGoal, new GoalDescription(), 1, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan2");
		ParentGoal weeklyGoal3 = new ParentGoal(monthlyGoal2, new GoalDescription(), 2, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan3");
		ParentGoal weeklyGoal4 = new ParentGoal(monthlyGoal3, new GoalDescription(), 2, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan4");
		ParentGoal weeklyGoal5 = new ParentGoal(monthlyGoal4, new GoalDescription(), 1, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan1_2016");
		
		dao.save(weeklyGoal5);
		dao.save(weeklyGoal4);
		dao.save(weeklyGoal3);
		dao.save(weeklyGoal2);
		dao.save(weeklyGoal);
		
		
		List<Goal> firstWeekOfjanuaryGoalsin2017 = dao.findWeeklyGoals(yearlyGoal.getTimeLabel(), 1);
		
		assertEquals(2, firstWeekOfjanuaryGoalsin2017.size());
		assertEquals(weeklyGoal.getTitle(), firstWeekOfjanuaryGoalsin2017.get(0).getTitle());
		
	}
	
	@Test
	public void daoShouldBeAbleToFindWDailyGoalsOfGivenWeek() throws Exception{
		
		ParentGoal yearlyGoal = new ParentGoal(new GoalDescription(), 2017, GoalScopeNames.YEARLY, "yearly_parentGoal_2017");
		ParentGoal yearlyGoal2 = new ParentGoal(new GoalDescription("Ých will mich mit andere Fremdsprache austatten"), 
				2016, GoalScopeNames.YEARLY, "yearly_parentGoal_2016");
		
		dao.save(yearlyGoal);
		dao.save(yearlyGoal2);
		
		ParentGoal monthlyGoal = new ParentGoal(yearlyGoal, new GoalDescription(), Calendar.JANUARY, GoalScopeNames.MONTHLY, "monthly_parentGoal_jan");
		ParentGoal monthlyGoal2 = new ParentGoal(yearlyGoal, new GoalDescription(), Calendar.JANUARY, GoalScopeNames.MONTHLY, "monthly_parentGoal_jan2");
		ParentGoal monthlyGoal3 = new ParentGoal(yearlyGoal, new GoalDescription(), Calendar.FEBRUARY, GoalScopeNames.MONTHLY, "monthly_parentGoal_feb");
		ParentGoal monthlyGoal4 = new ParentGoal(yearlyGoal2, new GoalDescription(), Calendar.JANUARY, GoalScopeNames.MONTHLY, "monthly_parentGoal_feb2");
		
		dao.save(monthlyGoal);
		dao.save(monthlyGoal2);
		dao.save(monthlyGoal3);
		dao.save(monthlyGoal4);
		
		ParentGoal weeklyGoal = new ParentGoal(monthlyGoal, new GoalDescription(), 1, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan");
		ParentGoal weeklyGoal2 = new ParentGoal(monthlyGoal, new GoalDescription(), 1, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan2");
		ParentGoal weeklyGoal3 = new ParentGoal(monthlyGoal2, new GoalDescription(), 2, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan3");
		ParentGoal weeklyGoal4 = new ParentGoal(monthlyGoal3, new GoalDescription(), 2, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan4");
		ParentGoal weeklyGoal5 = new ParentGoal(monthlyGoal4, new GoalDescription(), 1, GoalScopeNames.WEEKLY, "weekly_parentGoal_jan1_2016");
		
		dao.save(weeklyGoal5);
		dao.save(weeklyGoal4);
		dao.save(weeklyGoal3);
		dao.save(weeklyGoal2);
		dao.save(weeklyGoal);
		
		ParentGoal dailyGoal = new ParentGoal(weeklyGoal, new GoalDescription(), Calendar.MONDAY, GoalScopeNames.DAILY, "daily_monday_jan_1st_week");
		ParentGoal dailyGoal2 = new ParentGoal(weeklyGoal, new GoalDescription(), Calendar.MONDAY, GoalScopeNames.DAILY, "daily_monday_jan_1st_week_2");
		ParentGoal dailyGoal3 = new ParentGoal(weeklyGoal3, new GoalDescription(), Calendar.TUESDAY, GoalScopeNames.DAILY, "daily_tuesday_jan_2nd_week");
		ParentGoal dailyGoal4 = new ParentGoal(weeklyGoal2, new GoalDescription(), Calendar.MONDAY, GoalScopeNames.DAILY, "daily_monday_jan_1st_week2");
		ParentGoal dailyGoal5 = new ParentGoal(weeklyGoal5, new GoalDescription(), Calendar.MONDAY, GoalScopeNames.DAILY, "daily_monday_jan_1st_week_2016");
		
		dao.save(dailyGoal);
		dao.save(dailyGoal2);
		dao.save(dailyGoal3);
		dao.save(dailyGoal4);
		dao.save(dailyGoal5);
		
		List<Goal> mondayGoalsOfJanuary217FirstWeek = dao.findDailyGoals(yearlyGoal.getTimeLabel(), weeklyGoal.getTimeLabel(), Calendar.MONDAY);
		
		assertEquals(3, mondayGoalsOfJanuary217FirstWeek.size());
		assertEquals(dailyGoal.getTitle(), mondayGoalsOfJanuary217FirstWeek.get(0).getTitle());
		
	}
	
}
